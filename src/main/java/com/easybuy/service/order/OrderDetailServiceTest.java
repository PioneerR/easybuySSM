package com.easybuy.service.order;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.easybuy.entity.OrderDetail;
import com.easybuy.entity.User;
import com.easybuy.entity.UserAddress;

public class OrderDetailServiceTest {
	private static final Logger logger = Logger.getLogger(OrderDetailServiceTest.class);
	@Test
	public void test(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-public.xml");
		OrderDetailService orderDetailService=(OrderDetailService) ctx.getBean("orderDetailService");
		logger.debug(orderDetailService);
		OrderDetail od = new OrderDetail();
		od.setOrderId(9888);
		od.setProductId(2);
		od.setCost(9999);
		od.setQuantity(10);
		boolean flag =orderDetailService.addNewOrderDetail(od);
	}
	@Test
	public void test1(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-public.xml");
		OrderService orderService =(OrderService) ctx.getBean("orderService");
		User user = new User();
		user.setLoginName("小明");
		user.setId(99);
		UserAddress userAddress = new UserAddress();
		userAddress.setAddress("福建省厦门市湖里区");
		double cost =9999;
		String serialNumber ="5131386546";
		Integer status =1;
		Integer payType =2;
		boolean flag =orderService.addNewOrder(user, userAddress, cost, status, payType,serialNumber);
		logger.debug("---------------->"+flag);
	};
}
