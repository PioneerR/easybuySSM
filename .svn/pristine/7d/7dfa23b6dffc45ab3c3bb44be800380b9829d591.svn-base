package com.easybuy.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.easybuy.entity.Product;


public class ShoppingCart {
	public List<ShoppingCartItem> items = new LinkedList<ShoppingCartItem>();
	private double sum;
	
	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	/**
	 * 获取购物车中所有商品
	 * @return
	 */
	public List<ShoppingCartItem> getItems() {
		return items;
	}

	public void setItems(List<ShoppingCartItem> items) {
		this.items = items;
	}

	/**
	 * 往购物车添加一项商品
	 * @param product
	 * @param quantity
	 */
	public void addItem(Product product, int quantity){
		int flag =0;

		for(int i=0;i<items.size();i++){
			//判断购物车中是否已有此商品，如果有则累计数量
			ShoppingCartItem item = items.get(i);
			if(item.getProduct().getId()==(product.getId())){
				if(item.getQuantity()+quantity>product.getStock()){
					System.out.println("商品数量不足");
					flag =1;
				}else{
					item.setQuantity(item.getQuantity()+quantity);
					item.setCost(item.getCost()+item.getProduct().getPrice()*item.getQuantity());
					flag =1;
				}
			}		
		}
	
		if(flag==0){
			ShoppingCartItem item = new ShoppingCartItem();
			item.setProduct(product);
			item.setQuantity(quantity);
			item.setCost(product.getPrice()*quantity);
			items.add(item);
		}
	}

}
