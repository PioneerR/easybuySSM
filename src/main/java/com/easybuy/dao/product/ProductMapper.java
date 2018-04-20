package com.easybuy.dao.product;

import com.easybuy.entity.Product;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface ProductMapper {

	/**
	 * 增加产品信息
	 * @param 
	 * @return
	 */
	public int addProduct(Product p);

	/**
	 * 通过产品id删除产品信息
	 * @param 
	 * @return
	 */
	public int deleteProductById(@Param("id")Integer id);

	/**
	 * 通过id修改产品信息
	 * @param 
	 * @return
	 */
	public int updateProduct(Product p);

	/**
	 * 查询所有的产品信息
	 * @return
	 */
	public List<Product> getListProductByCategoryId(@Param("categoryId")Integer categoryId);

	/**
	 * 根据产品id查询产品信息
	 * @param 
	 * @return
	 */
	public Product getProductById(@Param("id")Integer id);
	

}
