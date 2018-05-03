package com.easybuy.service.order;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easybuy.dao.order.OrderMapper;
import com.easybuy.entity.User;
import com.easybuy.entity.UserAddress;
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

}
