package com.easybuy.servlet.pre;

import com.easybuy.entity.Product;
import com.easybuy.service.category.CategoryService;
import com.easybuy.service.product.ProductService;
import com.easybuy.servlet.web.AbstractServlet;
import com.easybuy.util.CategoryVo;
import com.easybuy.util.Page;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

@WebServlet("/pre/productServlet")
public class ProductServlet extends AbstractServlet  {

	private ProductService productService;	
	private CategoryService categoryService;

	public void init() throws ServletException {		
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();	
		this.productService=(ProductService)context.getBean("productService");
		this.categoryService=(CategoryService)context.getBean("categoryService");
	}

	public Class getServletClass() {
		return ProductServlet.class;
	}	
	
	public String getListProductByCategoryLevelId(HttpServletRequest request,
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
		
		
		List<CategoryVo> vo1List=categoryService.getListAllCategory();
        //封装返回
        request.setAttribute("vo1List", vo1List);
        
		String categoryId=request.getParameter("categoryLevelId");
		//获取首页数据
		List<Product> list=productService.getListProductByCategoryId(
				Integer.parseInt(categoryId),page.getStartIndex(),page.getPageSize());
		//int total=productService.getProductCountByCategoryId(Integer.parseInt(categoryId));
		int total=productService.getProductCount(Integer.parseInt(categoryId),null);
		request.setAttribute("total", total);
		request.setAttribute("productList", list);
		//根据menu的值，显示不同的leftBar的css样式
		request.setAttribute("menu",5);
		//取到数据后，转发到categoryList.jsp页面，这个页面在/backend下
		return "/pre/product/productList";
	}
	
	public String getProductDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		String id=request.getParameter("id");
		Product p=productService.getProductById(Integer.parseInt(id));		
		List<CategoryVo> vo1List=categoryService.getListAllCategory();    
        request.setAttribute("vo1List", vo1List);        
		request.setAttribute("product", p);		
		return "/pre/product/productDetail";
	}
	
	public String getListProduct(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		//分页功能
		int totalCount=productService.getTotalCount();
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
		
		//取得数据
		List<CategoryVo> vo1List=categoryService.getListAllCategory();
        request.setAttribute("vo1List", vo1List);
        
<<<<<<< .mine
        String proName=request.getParameter("proName");
        String categoryId=request.getParameter("categoryLevelId");
        int total=0;        
        List<Product> list=null;
        if(categoryId!=null){
        	int id=Integer.parseInt(categoryId);
        	list=productService.getListProduct(proName, id, page.getStartIndex(),page.getPageSize());
        	total=productService.getProductCount(id,proName);
        }else{
        	list=productService.getListProduct(proName, null, page.getStartIndex(),page.getPageSize());
        }
    
=======
		String proName=request.getParameter("proName");
		//获取首页数据
		List<Product> list=productService.getListProduct
				(proName, null, page.getStartIndex(),page.getPageSize());
>>>>>>> .r68
        //存储数据
		request.setAttribute("total", total);
		request.setAttribute("productList", list);
		request.setAttribute("menu",5);
		//返回页面
		return "/pre/product/productList";
	}
	
	
	
	
	
	

}
