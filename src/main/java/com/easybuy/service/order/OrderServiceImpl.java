package com.easybuy.service.order;


import com.easybuy.dao.order.OrderMapper;
import com.easybuy.entity.Order;
import com.easybuy.entity.User;
import com.easybuy.entity.UserAddress;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("orderService")
public class OrderServiceImpl implements OrderService{
 	@Autowired
	private OrderMapper orderMapper;
	@Override
	public boolean addNewOrder(User user, UserAddress userAddress, Double cost,
			Integer status, Integer payType,String serialNumber) {
		boolean flag =false;
		int result =orderMapper.addNewOrder(user, userAddress, cost, status, payType, serialNumber);
		if(result>0){
			flag =true;
		}
		return flag;
	}
	
	@Transactional(readOnly=true)
	public List<Order> findAllOrdersByUserId(int userId) {
		List<Order> list =orderMapper.getAllOrdersByUserId(userId);
		return list;
	}
	
	@Override
	public boolean updateOrderById(Order order) {
		boolean flag =false;
		int result=orderMapper.updateOrderById(order);
		if(result>0){	
			flag =true;
		}
		return flag;
	}
	
	@Transactional(readOnly=true)
	public Order findOrderById(int id) {
		Order order = orderMapper.getOrderById(id);
		return order;
	}
}
