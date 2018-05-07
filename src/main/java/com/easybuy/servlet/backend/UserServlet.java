package com.easybuy.servlet.backend;

import com.easybuy.entity.User;
import com.easybuy.service.user.UserService;
import com.easybuy.servlet.web.AbstractServlet;
import com.easybuy.util.Page;
import com.easybuy.util.ReturnResult;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

@WebServlet("/backend/userServlet")
public class UserServlet extends AbstractServlet {

	
	public Class getServletClass() {
		return UserServlet.class;
	}
	
	private UserService userService;
	
	public void init() throws ServletException {
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		this.userService=(UserService)context.getBean("userService");		
	}

	public String getListUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		//分页功能
		int totalCount=userService.getTotalCount();
		//创建Page对象的时候，totalCount\totalPageCount都初始化成功
		Page page=new Page(totalCount);
		String currentPageNoStr = request.getParameter("currentPageNo");
		String pageSizeStr=request.getParameter("pageSize");
		page.setUrl("backend/userServlet?action=getListUser");
		if(pageSizeStr!=null){
			int pageSize=Integer.parseInt(pageSizeStr);
			page.setPageSize(pageSize);
		}
		if(currentPageNoStr!=null){
			int currentPageNo=Integer.parseInt(currentPageNoStr);
			page.setCurrentPageNo(currentPageNo);
		}
		request.setAttribute("page", page);
		
		List<User> list=userService.getListUser(page.getStartIndex(),page.getPageSize());
		request.setAttribute("userList", list);
		
		return "/backend/user/userList";
	}
	
	public String toAddUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception{		
		return "/backend/user/addUser";
	}
	
	
	public ReturnResult addUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception{	
		ReturnResult result = new ReturnResult();
        
        
        System.out.println("userServlet addUser");
        
        result.returnSuccess();
        return result;
	}
	

}
