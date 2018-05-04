package com.easybuy.servlet.pre;

import com.easybuy.entity.News;
import com.easybuy.entity.User;
import com.easybuy.service.news.NewsService;
import com.easybuy.util.TimeUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebServlet(urlPatterns={"/newsServlet","/pre/newsServlet"})
public class NewsServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NewsService newsService;
	private News news;
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("resource")
	public void init(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-public.xml");
	/*	productService = new ProductServiceImpl();
		cartService = new CartServiceImpl();*/
		newsService =(NewsService) ctx.getBean("newsService");
		
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//因为有编码过滤器，因此可以不写以下的编码设置，以下表示封装数据时用的UTF-8
		//request.setCharacterEncoding("UTF-8");
		//传输的时候用UTF-8传输，完整写法response.setContentType("text/html;charset=UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		String action=request.getParameter("action");
		String id=request.getParameter("id");
		if(action!=null && action.equals("queryNewsList")){			
			List<News>  listNews=newsService.getAllNews();
			 request.setAttribute("news", listNews);
			
		}else if (action!=null && action.equals("newsDeatil")) {
			
			news=newsService.getNewsById(id);
			request.setAttribute("newscontent", news);
		}
		request.getRequestDispatcher("news.jsp").forward(request, response);
	//	response.sendRedirect("news.jsp");
	}
}