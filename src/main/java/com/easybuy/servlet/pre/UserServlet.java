package com.easybuy.servlet.pre;

import com.easybuy.entity.User;
import com.easybuy.service.user.UserService;
import com.easybuy.util.TimeUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebServlet(urlPatterns="/userServlet")
public class UserServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService;
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
		userService =(UserService) ctx.getBean("userService");
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//因为有编码过滤器，因此可以不写以下的编码设置，以下表示封装数据时用的UTF-8
		//request.setCharacterEncoding("UTF-8");
		//传输的时候用UTF-8传输，完整写法response.setContentType("text/html;charset=UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		UserService us= userService;
		HttpSession session=request.getSession();
		
		String action=request.getParameter("action");		
		if(action!=null && action.equals("register")){			
			String loginName=request.getParameter("loginName").trim();
			String password1=request.getParameter("password1").trim();
			String email=request.getParameter("email").trim();
			String phone=request.getParameter("phone").trim();
			
			//TODO 后期再考虑邀请人和邀请ID的用处
			/*String inviteName=request.getParameter("inviteName");
			if(inviteName!=null && !inviteName.equals("")){
				inviteName=inviteName.trim();
			}
			String inviteID=request.getParameter("inviteID");
			if(inviteID!=null && !inviteID.equals("")){
				inviteID=inviteID.trim();					
			}*/
			
			User u=new User();
			u.setLoginName(loginName);
			u.setPassword(password1);
			u.setEmail(email);
			u.setPhone(phone);
			//TimeUtil.getTime(new Date())获得的数据类型是Timestamp类型
			u.setCreateTime(TimeUtil.getDateTime(new Date()));	
			boolean flag=us.addUser(u);
			
			if(flag){
				//TODO 本句转移到登录页面去执行将用户存入session中
				session.setAttribute("loginName", loginName);
				//TODO 延时加载，并提示注册成功
				out.print("注册成功！3秒钟后跳转至登录页面...");
				//转发至主页面
				response.sendRedirect("Login.jsp");				
			}else{
				out.print("注册用户失败");
			}			
		}else if(action!=null && action.equals("login")){
			//TODO 考虑将userType取出来，进而判断应该显示管理员还是用户首页
			String loginName=request.getParameter("loginName").trim();
			String password=request.getParameter("password").trim(); 
			
			User user=us.loginCheck(loginName,password);
			//判断登陆成功与否,user!=null为成功登陆
			if(user!=null){
				//成功登陆后,根据不同type跳转不同页面
				//1=>管理员2=>商户3=>普通用户
				switch (user.getType()) {
				case 1:
					//TODO 跳转超级管理员
					System.err.println("超级管理员");
					break;
				case 2:
					System.err.println("商户");
					break;
				case 3:
					System.err.println("普通用户");
					break;
				default:
					out.print("loginError");
			//		throw new ServletException("用戶类型违法");
					System.err.println("用戶类型违法");
				}
				session.setAttribute("loginUser", user);
				out.print("loginSuccess");
			}else{
				out.print("loginError");
				//TODO 延时加载
			}
		}else if(action!=null && action.equals("exit")){
			session.invalidate();
			response.sendRedirect("Index.jsp");
		}else if(action!=null && action.equals("check")){
			String loginName=request.getParameter("loginName");
			String email=request.getParameter("email");
			String phone=request.getParameter("phone");
			
			boolean loginNameCheck=false;
			boolean emailCheck=false;
			boolean phoneCheck=false;
			//每一种情况都独立验证
			if(loginName!=null && !loginName.equals("") && email==null && phone==null){
				loginNameCheck=us.check("loginNameCheck",loginName);
				if(loginNameCheck==false){
					out.print("loginNameError");
				}else{
					out.print("loginNameSuccess");
				}
			}
			if(email!=null && !email.equals("") && loginName==null && phone==null){
				emailCheck=us.check("emailCheck",email);
				if(emailCheck==false){
					out.print("emailError");
				}else{
					out.print("emailSuccess");
				}
			}
			if(phone!=null && !phone.equals("") && loginName==null && email==null){
				phoneCheck=us.check("phoneCheck",phone);
				if(phoneCheck==false){
					out.print("phoneError");
				}else{
					out.print("phoneSuccess");
				}
			}
		}else{
			
		}

	}
}