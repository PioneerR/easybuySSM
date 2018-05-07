package com.easybuy.service.order;

import java.util.List;

import com.easybuy.entity.Order;
import com.easybuy.entity.User;
import com.easybuy.entity.UserAddress;
import com.easybuy.util.ShoppingCart;
import com.easybuy.util.ShoppingCartItem;

public interface OrderService {
	/**
	 * 添加新的订单信息
	 * @param user
	 * @param userAddress
	 * @param cost
	 * @param status
	 * @param payType
	 * @return
	 */
	public boolean addNewOrder(User user,UserAddress userAddress,Double cost,Integer status,Integer payType,String serialNumber);
	/**
	 * 根据用户Id查找订单信息
	 * @param userId
	 * @return
	 */
	public List<Order> findAllOrdersByUserId(int userId);
	/**
	 * 修改订单信息
	 * @param id
	 * @return
	 */
	public boolean updateOrderById(Order order);
	
	public Order findOrderById(int id);
	
}
