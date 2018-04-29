package com.easybuy.dao.product;

import com.easybuy.entity.Product;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface ProductMapper {

	/**
	 * 增加产品信息
	 * @param 
	 * @return
	 */
	public int addProduct(Product p)throws SQLException;

	/**
	 * 通过产品id删除产品信息
	 * @param 
	 * @return
	 */
	public int deleteProductById(@Param("id")Integer id)throws SQLException;

	/**
	 * 通过id修改产品信息
	 * @param 
	 * @return
	 */
	public int updateProduct(Product p)throws SQLException;

	/**
	 * 查询所有的产品信息
	 * @return
	 */
	public List<Product> getListProductByCategoryLevelId(@Param("categoryLevelId")Integer categoryLevelId,
			@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize)throws SQLException;

	/**
	 * 根据产品id查询产品信息
	 * @param 
	 * @return
	 */
	public Product getProductById(@Param("id")Integer id)throws SQLException;
	
	public int getProductCountByCategoryId(@Param("categoryId")Integer categoryId) throws SQLException;
	
	public int getTotalCount() throws SQLException;

}
