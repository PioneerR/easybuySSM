package com.easybuy.servlet.backend;

import com.easybuy.entity.Category;
import com.easybuy.entity.Product;
import com.easybuy.service.category.CategoryService;
import com.easybuy.service.product.ProductService;
import com.easybuy.servlet.web.AbstractServlet;
import com.easybuy.util.EmptyUtils;
import com.easybuy.util.Page;
import com.easybuy.util.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebServlet("/backend/productServlet")
public class ProductServlet extends AbstractServlet  {
	
	private ProductService productService;
	private CategoryService categoryService;
	//图片上传相关参数
	//图片缓存路径
	private static final String TMP_DIR_PATH = "c:\\tmp";
	private File tmpDir;
	private static final String DESTINATION_DIR_PATH = "/files";
	private File destinationDir;
	
	public void init() throws ServletException {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext-public.xml");	
		this.productService=(ProductService)context.getBean("productService");
		this.categoryService=(CategoryService)context.getBean("categoryService");
	}

	public Class getServletClass() {
		return ProductServlet.class;
	}
	
	
	public String index(HttpServletRequest request,
			HttpServletResponse response) throws Exception{

		//分页功能
		int totalCount=productService.getTotalCount();
		//创建Page对象的时候，totalCount\totalPageCount都初始化成功
		Page page=new Page(totalCount);
		String currentPageNoStr = request.getParameter("currentPageNo");
		String pageSizeStr=request.getParameter("pageSize");
		page.setUrl("backend/productServlet?action=index");
		if(pageSizeStr!=null){
			int pageSize=Integer.parseInt(pageSizeStr);
			page.setPageSize(pageSize);
		}
		if(currentPageNoStr!=null){
			int currentPageNo=Integer.parseInt(currentPageNoStr);
			page.setCurrentPageNo(currentPageNo);
		}
		request.setAttribute("page", page);
		
		//获取数据
		List<Product> list=productService.getListProductByCategoryLevelId(null,
				page.getStartIndex(),page.getPageSize());
		request.setAttribute("productList", list);
		//根据menu的值，显示不同的leftBar的css样式
		request.setAttribute("menu",5);
		//取到数据后，转发到categoryList.jsp页面，这个页面在/backend下
		return "/backend/product/productList";
	}
	
	
	public String addProduct(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		request.setAttribute("menu",6);
		request.setAttribute("product",new Product());
		//查询一级分类
		List<Category> list=categoryService.getListCategoryByLevel(1);
		request.setAttribute("categoryList1",list);		
		return "/backend/product/addProduct";
	}
	
	public void addProducted(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		//创建一个Map对象，用于存储解析表单的文本信息
		Map<String, String> params = new HashMap<String, String>();
		
		//创建产品对象
		Product product=null;
		
		//上传图片
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		fileItemFactory.setSizeThreshold(1 * 1024 * 1024); // 1 MB
		fileItemFactory.setRepository(tmpDir);
		String fileName = null;
		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		uploadHandler.setHeaderEncoding("utf-8");
		System.out.println("111111");
		try {
			List items = uploadHandler.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {
					//如果是表单属性，那就将其存到hashmap中
					params.put(item.getFieldName(), item.getString("utf-8"));
				} else {
					//若不是表单，即文件，则将文件存到指定路径，且文件不是空文件的情况下，才存入
					if (item.getSize() > 0) {
						//获取文件后缀
						fileName = item.getName().substring(
								item.getName().lastIndexOf("."));
						//TODO 有点不理解此处有两个fileName
						File file = new File(destinationDir, fileName);
						//给一个新的文件名，带后缀
						fileName = StringUtils.randomUUID()+ item.getName().substring(
								item.getName().lastIndexOf("."));
						//图片名与商品ID一致
						file = new File(destinationDir, fileName);
						//保存商品图片
						item.write(file);
					}
				}
			}
			System.out.println("22222222");
			//获取并设置产品信息并生成产品对象
			product=copyToProduct(params);
			//设置文件名
			product.setFileName(fileName);
			//0表示文件上传成功，即没有删除
			product.setIsDelete(0);
			//将产品添加到数据库中
			Integer id = product.getId();
			if (EmptyUtils.isEmpty(id) || id.equals("0")) {
				productService.addProduct(product);
            } else {
            	if(EmptyUtils.isEmpty(product.getFileName())|| product.getFileName().length()<5){
            		Product productDemo=productService.getProductById(id);
            		product.setFileName(productDemo.getFileName());
            	}
            	productService.updateProduct(product);
            }
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("333333");
		//返回产品页面，并显示添加的产品
		//return "/backend/productServlet?action=index";
		response.sendRedirect(request.getContextPath()+"/backend/productServlet?action=index");
	}
	
	private Product copyToProduct(Map<String,String> params)throws Exception{
		Product product=new Product();
		String id=params.get("id");
		String name=params.get("name");
		String describe=params.get("describe");
		String price=params.get("price");
		String stock=params.get("stock");
		String categoryLevel1Id=params.get("categoryLevel1Id");
		String categoryLevel2Id=params.get("categoryLevel2Id");
		String categoryLevel3Id=params.get("categoryLevel3Id");
		product.setName(name);
		product.setDescribe(describe);
		product.setPrice(Float.valueOf(price));
		product.setStock(Integer.parseInt(stock));
		product.setCategoryLevel1Id(EmptyUtils.isNotEmpty(categoryLevel1Id)?Integer.parseInt(categoryLevel1Id):null);
		product.setCategoryLevel2Id(EmptyUtils.isNotEmpty(categoryLevel2Id)?Integer.parseInt(categoryLevel2Id):null);
		product.setCategoryLevel3Id(EmptyUtils.isNotEmpty(categoryLevel3Id)?Integer.parseInt(categoryLevel3Id):null);
		product.setId(EmptyUtils.isNotEmpty(id)?Integer.parseInt(id):null);
		return product;
	}
	

}
