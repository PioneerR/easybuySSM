package com.easybuy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
//@WebFilter(urlPatterns="")
public class LoginCheckFilter implements Filter {
	private Logger log=Logger.getLogger("mylog");
	public LoginCheckFilter() {	
	}

	private FilterConfig filterConfig;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filterChain) {

		try {
			log.info("进入LoginCheckFilter的doFilter()方法");
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;
			//false表示，如果session为空，则不创建session
			//默认为true，表示session为null时，则创建一个
			HttpSession session = request.getSession(false);
			//TODO 只要进入网站的任何页面，不是都会有session？
			if(session!=null && session.getAttribute("loginName")!=null){
				filterChain.doFilter(request, response);
			}else{
				response.sendRedirect("login.jsp");
			}
			log.info("退出LoginCheckFilter的doFilter()方法");
		} catch (IOException e) {
			log.error(e);
		} catch (ServletException e) {
			log.error(e);
		}
	}

	public void destroy() {

	}
}

