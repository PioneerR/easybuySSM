package com.easybuy.servlet.web;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 公共的servlet抽象类
 * @author lrg
 */

public abstract class AbstractServlet extends HttpServlet {

	public abstract Class getServletClass();
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("action");
		Method method=null;
		Object obj=null;
		
		
		
		

	}
}

















