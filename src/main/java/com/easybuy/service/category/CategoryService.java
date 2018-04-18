package com.easybuy.service.category;

import com.easybuy.entity.Category;

import java.util.List;

public interface CategoryService {

	/**
	 * 增加
	 * @param 
	 * @return
	 */
	public boolean addCategory(Category c);

	/**
	 * 删除
	 * @param 
	 * @return
	 */
	public boolean deleteCategoryById(int id);

	/**
	 * 修改
	 * @param 
	 * @return
	 */
	public boolean updateCategory(Category c);

	/**
	 * 查询
	 * @return
	 */
	public List<Category> getAllCategory();

	/**
	 * 根据id查询
	 * @param 
	 * @return
	 */
	public Category getCategoryById(int id);


	
}
