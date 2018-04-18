package com.easybuy.dao.product;

import com.easybuy.entity.Product;

import java.sql.ResultSet;


public interface ProductMapper {

	/**
	 * 增加
	 * @param 
	 * @return
	 */
	public int addProduct(Product p);

	/**
	 * 删除
	 * @param 
	 * @return
	 */
	public int deleteProductById(int id);

	/**
	 * 修改
	 * @param 
	 * @return
	 */
	public int updateProduct(Product p);

	/**
	 * 查询
	 * @return
	 */
	public ResultSet getAllProduct();

	/**
	 * 根据id查询
	 * @param 
	 * @return
	 */
	public ResultSet getProductById(int id);
	
	
}
