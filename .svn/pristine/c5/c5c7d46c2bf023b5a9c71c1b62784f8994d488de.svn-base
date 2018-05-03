package com.easybuy.servlet.pre;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.easybuy.entity.OrderDetail;
import com.easybuy.entity.User;
import com.easybuy.entity.UserAddress;
import com.easybuy.service.order.OrderDetailService;
import com.easybuy.service.order.OrderService;
import com.easybuy.service.user.UserAddressService;
import com.easybuy.service.user.UserService;
import com.easybuy.servlet.web.AbstractServlet;
import com.easybuy.util.ShoppingCart;
import com.easybuy.util.ShoppingCartItem;
@WebServlet("/OrderServlet")
public class OrderServlet extends AbstractServlet {
	private static final Logger logger =Logger.getLogger(OrderServlet.class);
	private OrderDetailService orderDetailService;
	private OrderService orderService;
	private UserService userService;
	private UserAddressService userAddressService;
	
	public void init(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-public.xml");
		orderService = (OrderService) ctx.getBean("orderService");
		orderDetailService = (OrderDetailService) ctx.getBean("orderDetailService");
		userService = (UserService) ctx.getBean("userService");
		userAddressService = (UserAddressService) ctx.getBean("userAddressService");
		
	}
	public void addNewOrder(HttpServletRequest request,HttpServletResponse response){
		String payTypeStr =request.getParameter("payType");
		String dispatchType =request.getParameter("dispatchType");
		String userIdStr = request.getParameter("userId");
		String statusStr = request.getParameter("status");
		int userId = Integer.parseInt(userIdStr);
		int status = Integer.parseInt(statusStr);
		int payType = Integer.parseInt(payTypeStr);
	System.out.println("userId"+userId);
	System.out.println("status"+status);
	System.out.println("payType"+payType);
		User user =userService.getUserById(userId);
		UserAddress userAddress = userAddressService.getUserAddressByUserId(userId);
		
	System.out.println("user"+user);
	System.out.println("userAddress"+userAddress);
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
		List<ShoppingCartItem>list = cart.items;
		double sum = cart.getSum();
		String serialNumberStr = "323423432";
		int serialNumber = Integer.parseInt(serialNumberStr);
		boolean flag =orderService.addNewOrder(user, userAddress, sum, status, payType, serialNumberStr);
		logger.debug("payType----->"+payType);
		logger.debug("dispatchType----->"+dispatchType);
		OrderDetail orderDetail =null;
		for(ShoppingCartItem item:list){
			orderDetail = new OrderDetail();
			int productId = item.getProduct().getId();
			int quantity = item.getQuantity();
			double cost = item.getCost();
			orderDetail.setQuantity(quantity);
			orderDetail.setCost(cost);
			orderDetail.setProductId(productId);
			orderDetail.setOrderId(serialNumber);
			orderDetailService.addNewOrderDetail(orderDetail);
		}
	}
	
	
	
	/**
	 * 返回反射对象
	 */
	@Override
	public Class getServletClass() {	
		return OrderServlet.class;
	}

}
