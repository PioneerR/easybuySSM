package com.easybuy.servlet.pre;

import com.easybuy.entity.Category;
import com.easybuy.service.category.CategoryService;
import com.easybuy.service.product.ProductService;
import com.easybuy.servlet.web.AbstractServlet;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebServlet("/home")
public class HomeServlet extends AbstractServlet {
	
	private ProductService productService;
	private CategoryService categoryService;
	
	public void init() throws ServletException {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext-public.xml");	
		this.productService=(ProductService)context.getBean("productService");
		this.categoryService=(CategoryService)context.getBean("categoryService");
	}

	public Class getServletClass() {
		return HomeServlet.class;
	}

	public String index(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		List<Category> list1=categoryService.getListCategoryByLevel(1);
		List<Category> list2=categoryService.getListCategoryByLevel(2);
		List<Category> list3=categoryService.getListCategoryByLevel(3);
		request.setAttribute("categoryList1", list1);
		request.setAttribute("categoryList2", list2);
		request.setAttribute("categoryList3", list3);
		
		return "index";
	}

	


}
