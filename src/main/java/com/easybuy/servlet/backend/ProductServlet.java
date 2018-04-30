package com.easybuy.servlet.backend;

import com.easybuy.entity.Category;
import com.easybuy.entity.Product;
import com.easybuy.service.category.CategoryService;
import com.easybuy.service.product.ProductService;
import com.easybuy.servlet.web.AbstractServlet;
import com.easybuy.util.EmptyUtils;
import com.easybuy.util.Page;
import com.easybuy.util.ReturnResult;
import com.easybuy.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private static final String TMP_DIR_PATH = "C:/ProgramData/TEMP";
	private File tmpDir;
	private static final String DESTINATION_DIR_PATH = "/files";
	private File destinationDir;
	
	public void init() throws ServletException {
		//File用于创建缓存文件的文件夹
		tmpDir = new File(TMP_DIR_PATH);
		if (!tmpDir.exists()) {
			//如果目录不存在，则新建文件夹
			tmpDir.mkdirs();
		}
		//获取上传图片的路径
		String realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH);
		destinationDir = new File(realPath);
		if(!destinationDir.exists()){
			destinationDir.mkdirs();			
		}
		if (!destinationDir.isDirectory()) {
			throw new ServletException(DESTINATION_DIR_PATH
					+ " is not a directory");
		}
		//初始化service
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
		
		//获取首页数据
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
		
		//使用文件上传组件处理文件上传
		//1、创建一个DiskFileItemFactory工厂
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		//设置最多只允许在内存中存储的数据大小：1 MB,单位:字节,该内存是给JVM使用的内存空间，相当于JVM的工作空间大小
		//一旦上传文件超出这个限值，在内存中无法保存该文件内容，就会将超出的数据存放在硬盘中，解析时再调用
		fileItemFactory.setSizeThreshold(1 * 1024 * 1024);
		//设置一旦文件超过getSizeThreshold()的值时，数据存放在硬盘的目录
		fileItemFactory.setRepository(tmpDir);
		
		String fileName = null;
		//2、创建一个文件上传解析器
		//使用ServletFileUpload()默认构造器创建未初始化的时候
		//需要以factory作为构造函数或者setFileItemFactory()方法对factory进行配置
		ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
		//设置上传文件最大值
		upload.setSizeMax(2000000);
		upload.setHeaderEncoding("utf-8");
		try {
			//解析表单中的数据
			List<FileItem> items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			
			// 正则匹配文件名
			String regExp = ".+\\.(.+)$";
			//过滤掉的文件类型
			String [] errorType={".exe",".com",".cgi",".jsp"};
			//pattern在util.regex包
			Pattern p=Pattern.compile(regExp);
			
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {
					//如果是表单属性，那就将其存到hashmap中
					params.put(item.getFieldName(), item.getString("utf-8"));
				} else {
					//若不是表单，即文件，则将文件存到指定路径，且文件不是空文件的情况下，才存入
					if (item.getSize() > 0) {						
						//获取上传文件的文件名,不包含文件路径
						fileName = item.getName();
						//获取上传文件的大小
						long size=item.getSize();
						
						//如果拿到的文件对象是上传文件的类型，但文件名和大小有误，那么继续拿下一个文件对象
						if(fileName==null || fileName.equals("") && size==0){							
							continue;
						}
						
						Matcher m=p.matcher(fileName);
						boolean flag=m.find();
						if(flag){
							//过滤不符合格式的数据文件
							for(int i=0;i<errorType.length;i++){
								if(m.group(1).endsWith(errorType[i])){
									throw new IOException(fileName+":wrong type");
								}
							}
							
							//更改上传的文件名，UUID+文件名的后缀							
							fileName = StringUtils.randomUUID()+
									item.getName().substring(item.getName().lastIndexOf("."));
							//创建文件，图片名与商品ID一致
							File file = new File(destinationDir, fileName);							
							//保存商品图片
							item.write(file);
							log.info("上传文件"+fileName+"至路径："+destinationDir + "成功！！&nbsp;文件大小：" + size);							
							//如果文件保存在临时文件夹中，会清空临时文件，一般写在最后，因为要将文件上传完毕，才能删除临时文件
							item.delete();
						}else{
							throw new IOException("fail to upload");
						}
					}
				}
			}
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
            	//如果id存在，那么更新信息
            	if(EmptyUtils.isEmpty(product.getFileName())|| product.getFileName().length()<5){
            		Product productDemo=productService.getProductById(id);
            		product.setFileName(productDemo.getFileName());
            	}
            	productService.updateProduct(product);
            }
		}catch (Exception e){
			e.printStackTrace();
		}
		//返回产品页面，并显示添加的产品,使用重定向再一次请求数据
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
	
	public ReturnResult deleteProductById(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		ReturnResult result=new ReturnResult();
		String id=request.getParameter("id");
		productService.deleteProductById(Integer.parseInt(id));
		result.returnSuccess();
		return result;
	}
	
	public String updateProduct(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String id=request.getParameter("id");
		Product product=productService.getProductById(Integer.parseInt(id));		
		request.setAttribute("menu",6);
		
		//查询分类		
		List<Category> list1=categoryService.getListCategoryByLevel(1);
		List<Category> list2=categoryService.getListCategoryByLevel(2);
		List<Category> list3=categoryService.getListCategoryByLevel(3);
		//存储分类列表
		request.setAttribute("categoryList1", list1);
		request.setAttribute("categoryList2", list2);
		request.setAttribute("categoryList3", list3);
		request.setAttribute("product", product);
		return "/backend/product/addProduct";
	}
	
	

}
