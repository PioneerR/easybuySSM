package com.easybuy.service.cart;

import com.easybuy.util.ShoppingCart;
import com.easybuy.util.ShoppingCartItem;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
@Service("cartService")
public class CartServiceImpl implements CartService{
	private static final Logger logger = Logger.getLogger(CartServiceImpl.class);

	/**
	 * 计算购物车消费信息
	 */
	@Override
	public ShoppingCart calculate(ShoppingCart cart) {
		double sum =0;
		List<ShoppingCartItem> list = cart.items;
		for(ShoppingCartItem item :list){
			item.setCost(item.getProduct().getPrice()*item.getQuantity());
			sum = sum +item.getCost();
		}
		cart.setSum(sum);
		logger.debug("购物车总花费"+cart.getSum());
		return cart;
	}

	@Override
	public List<ShoppingCartItem> removeShoppingItem(ShoppingCart cart,int delId) {
		List<ShoppingCartItem>list = cart.items;
		Iterator it =list.iterator();
		while(it.hasNext()){
			ShoppingCartItem item =(ShoppingCartItem) it.next();
			if(item.getProduct().getId()==delId){
				it.remove();
			}
		}
		return cart.items;
	}

}
