package com.easybuy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
@WebFilter(urlPatterns={"/*"},initParams={@WebInitParam(name = "charset", value = "UTF-8")})
public class CharacterEncodingFilter implements Filter {

	private String charset;
	public void init(FilterConfig arg0) throws ServletException {
		String initParam=arg0.getInitParameter("charset").trim();
		if(initParam != null && initParam.length()!=0){
			charset=initParam;
		}
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		if(charset!=null){
			arg0.setCharacterEncoding(charset);
			arg1.setCharacterEncoding(charset);			
		}
		arg2.doFilter(arg0, arg1);
	}
	
	public void destroy() {
		
	}

}
