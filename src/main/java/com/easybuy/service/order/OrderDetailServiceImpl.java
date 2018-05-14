package com.easybuy.service.order;

import com.easybuy.dao.order.OrderDetailMapper;
import com.easybuy.entity.OrderDetail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED)
@Service("orderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {
	
	@Autowired(required=false)
	@Qualifier("orderDetailMapper")
	private OrderDetailMapper orderDetailMapper;
	
	@Override
	public boolean addNewOrderDetail(OrderDetail orderDetail){
		boolean flag =false;
		 int result=orderDetailMapper.addNewOrderDetail(orderDetail);
		 if(result>0){
			 flag =true;	 
		 }
		 return flag;
	}

	@Transactional(readOnly=true)
	public List<OrderDetail> listOrderDetailBySerialNumber(String serialNumber) {
		List<OrderDetail>list = orderDetailMapper.listOrderDetailBySerialNumber(serialNumber);
		return list;
	}

}
