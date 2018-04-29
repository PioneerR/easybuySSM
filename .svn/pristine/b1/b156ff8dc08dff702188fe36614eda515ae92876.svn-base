package com.easybuy.dao.category;

import com.easybuy.entity.Category;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CategoryMapper {
	/**
	 * 增加类别
	 * @param c
	 * @return
	 */
	public int addCategory(Category c)throws SQLException;
	
	/**
	 * 删除类别
	 * @param CategoryId
	 * @return
	 */
	public int deleteCategoryById(@Param("id")Integer id)throws SQLException;
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public int updateCategory(Category c)throws SQLException;
	
	/**
	 * 查询所有类别
	 * @return
	 */
	public List<Category> getListCategory(
			@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize)throws SQLException;
	
	/**
	 * 根据类别id查询类别
	 * @param categoryId
	 * @return
	 */
	public Category getCategoryById(@Param("id")Integer id)throws SQLException;
	
	public int getTotalCount() throws SQLException;
	
	public List<Category> getListCategoryByLevel(@Param("level")Integer level)throws SQLException;
	
	public List<Category> getListCategoryByParentId(@Param("parentId")Integer parentId)throws SQLException;
	
}
