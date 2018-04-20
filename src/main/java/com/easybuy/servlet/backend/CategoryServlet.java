package com.easybuy.servlet.backend;

import com.easybuy.entity.Category;
import com.easybuy.service.category.CategoryService;
import com.easybuy.service.category.CategoryServiceImpl;
import com.easybuy.servlet.web.AbstractServlet;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/category")
public class CategoryServlet extends AbstractServlet {

	private CategoryService categoryService;
	
	public void init() throws ServletException {
		this.categoryService=new CategoryServiceImpl();
	}
	
	public Class getServletClass() {
		return CategoryServlet.class;
	}

	public String getListCategoryByLevel(HttpServletRequest request,
				HttpServletResponse response) throws Exception{
		String level=request.getParameter("level");		
		List<Category> list=categoryService.getListCategoryByLevel(Integer.parseInt(level));
		request.setAttribute("categoryList", list);
		
		return "/backend/category/categoryList";
	}
	

}
