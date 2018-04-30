package com.easybuy.servlet.pre;

import com.easybuy.entity.Product;
import com.easybuy.service.category.CategoryService;
import com.easybuy.service.product.ProductService;
import com.easybuy.servlet.web.AbstractServlet;
import com.easybuy.util.Page;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebServlet("/pre/productServlet")
public class ProductServlet extends AbstractServlet  {
	
	private ProductService productService;
	private CategoryService categoryService;

	public void init() throws ServletException {		
		//初始化service
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext-public.xml");	
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
		
		String categoryLevelId=request.getParameter("categoryLevelId");
		//获取首页数据
		List<Product> list=productService.getListProductByCategoryLevelId(
				Integer.parseInt(categoryLevelId),page.getStartIndex(),page.getPageSize());
		request.setAttribute("productList", list);
		//根据menu的值，显示不同的leftBar的css样式
		request.setAttribute("menu",5);
		//取到数据后，转发到categoryList.jsp页面，这个页面在/backend下
		return "/pre/product/productList";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
