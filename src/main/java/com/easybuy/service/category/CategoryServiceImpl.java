package com.easybuy.service.category;


import com.easybuy.dao.category.CategoryMapper;
import com.easybuy.entity.Category;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
	
	private List<Category> list=new ArrayList();
	boolean flag=false;
	private Logger log=Logger.getLogger(CategoryServiceImpl.class);
	private Object obj=null;
	
	@Autowired(required=false)
	private CategoryMapper categoryMapper=null;
	
	public boolean addCategory(Category c) {
		try {
			int sqlNum=categoryMapper.addCategory(c);
			if(sqlNum>0){
				flag=true;
			}
		} catch (Exception e) {
			log.error(e);
		}
		return flag;
	}

	public boolean deleteCategoryById(int id) {
		try {
			int sqlNum=categoryMapper.deleteCategoryById(id);
			if(sqlNum>0){
				flag=true;
			}
		} catch (Exception e) {
			log.error(e);
		}
		return flag;
	}

	public boolean updateCategory(Category c) {
		try {
			int sqlNum=categoryMapper.updateCategory(c);
			if(sqlNum>0){
				flag=true;
			}
		} catch (Exception e) {
			log.error(e);
		} 
		return flag;
	}

	public Category getCategoryById(int id) {
		Category c=null;
		try {
			c=categoryMapper.getCategoryById(id);
		} catch (Exception e) {
			log.error(e);
		}
		return c;
	}
	
	public List<Category> getListCategory(int startIndex,int pageSize) {
		try {
			list=categoryMapper.getListCategory(startIndex,pageSize);
		} catch (Exception e) {
			log.error(e);
		}
		return list;
	}

	public int getTotalCount() {
		int totalCount=0;
		try {
			totalCount = categoryMapper.getTotalCount();
		} catch (SQLException e) {
			log.error(e);
		}
		return totalCount;
	}

	@Override
	public List<Category> getListCategoryByLevel(int level) {
		try {
			list=categoryMapper.getListCategoryByLevel(level);
		} catch (Exception e) {
			log.error(e);
		}
		return list;
	}

	@Override
	public List<Category> getListCategoryByParentId(int parentId) {
		try {
			list=categoryMapper.getListCategoryByParentId(parentId);
		} catch (Exception e) {
			log.error(e);
		}
		return list;
	}
	
	
}
