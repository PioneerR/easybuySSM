package com.easybuy.dao.category;

import com.easybuy.entity.Category;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CategoryMapper {
	/**
	 * 增加类别
	 * @param c
	 * @return
	 */
	public int addCategory(Category c);
	
	/**
	 * 删除类别
	 * @param CategoryId
	 * @return
	 */
	public int deleteCategoryById(@Param("categoryId")Integer categoryId);
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public int updateCategory(Category c);
	
	/**
	 * 查询所有类别
	 * @return
	 */
	public List<Category> getAllCategories();
	
	/**
	 * 根据类别id查询类别
	 * @param categoryId
	 * @return
	 */
	public Category getCategoryById(@Param("categoryId")Integer categoryId);
	
	/**
	 * 根据level查询类别
	 * @param level
	 * @return
	 */
	public List<Category> getCategoriesByLevel(@Param("grade")Integer level);
}
