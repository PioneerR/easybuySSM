package com.easybuy.servlet.backend;

import com.easybuy.service.product.ProductService;
import com.easybuy.service.product.ProductServiceImpl;
import com.easybuy.servlet.web.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/backend/productServlet")
public class ProductServlet extends AbstractServlet  {

	private ProductService ps;
	public void init() throws ServletException {
		ps=new ProductServiceImpl();
	}

	public Class getServletClass() {
		return ProductServlet.class;
	}
	
	
	

}
