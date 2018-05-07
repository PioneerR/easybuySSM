package com.easybuy.dao.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.easybuy.entity.OrderDetail;

public interface OrderDetailMapper {
	
	/**
	 * 增加新的商品订单详情
	 * @param orderId
	 * @param productId
	 * @param quantity
	 * @param cost
	 * @return
	 */
	public int addNewOrderDetail(@Param("orderDetail")OrderDetail orderDetail);
	public List<OrderDetail> listOrderDetailBySerialNumber(@Param("serialNumber")String serialNumber);
}
