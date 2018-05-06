package com.easybuy.service.category;


import com.easybuy.dao.category.CategoryMapper;
import com.easybuy.entity.Category;
import com.easybuy.util.CategoryVo;
import com.easybuy.util.EmptyUtils;
import com.easybuy.util.RedisUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
	
	private List<Category> list=new ArrayList();
	boolean flag=false;
	private Logger log=Logger.getLogger(CategoryServiceImpl.class);
	
	@Autowired(required=false)
	private CategoryMapper categoryMapper=null;
	
	public boolean addCategory(Category c){
		try {
			int sqlNum=categoryMapper.addCategory(c);
			if(sqlNum>0){
				flag=true;
				if(RedisUtil.existsObject("vo1List")){
		        	RedisUtil.deleteKeyObject("vo1List");        	
		        }
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return flag;
	}

	public boolean deleteCategoryById(int id) {
		try {
			int sqlNum=categoryMapper.deleteCategoryById(id);
			if(sqlNum>0){
				flag=true;
				if(RedisUtil.existsObject("vo1List")){
		        	RedisUtil.deleteKeyObject("vo1List");        	
		        }
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return flag;
	}

	public boolean updateCategory(Category c) {
		try {
			int sqlNum=categoryMapper.updateCategory(c);
			if(sqlNum>0){
				flag=true;
				if(RedisUtil.existsObject("vo1List")){
		        	RedisUtil.deleteKeyObject("vo1List");        	
		        }
			}
		} catch (SQLException e) {
			log.error(e);
		} 
		return flag;
	}

	@Transactional(readOnly=true)
	public Category getCategoryById(int id) {
		Category c=null;
		try {
			c=categoryMapper.getCategoryById(id);
		} catch (SQLException e) {
			log.error(e);
		}
		return c;
	}
	
	@Transactional(readOnly=true)
	public List<Category> getListCategory(int startIndex,int pageSize) {
		try {
			list=categoryMapper.getListCategory(startIndex,pageSize);
		} catch (SQLException e) {
			log.error(e);
		}
		return list;
	}
	
	@Transactional(readOnly=true)
	public int getTotalCount() {
		int totalCount=0;
		try {
			totalCount = categoryMapper.getTotalCount();
		} catch (SQLException e) {
			log.error(e);
		}
		return totalCount;
	}

	@Transactional(readOnly=true)
	public List<Category> getListCategoryByLevel(int level) {
		try {
			list=categoryMapper.getListCategoryByLevel(level);
		} catch (SQLException e) {
			log.error(e);
		}
		return list;
	}

	@Transactional(readOnly=true)
	public List<Category> getListCategoryByParentId(int parentId) {
		try {
			list=categoryMapper.getListCategoryByParentId(parentId);
		} catch (SQLException e) {
			log.error(e);
		}
		return list;
	}

	@Override
	public List<CategoryVo> getListAllCategory() {
		
		List<CategoryVo> vo1List=(List<CategoryVo>) RedisUtil.getObject("vo1List");
		if(EmptyUtils.isNotEmpty(vo1List)){
			log.info("从Redis中取出vo1List");
		}else{
			//分类列表集合封装一级CategoryVo,一个CategoryVo可以取出一个Category
			vo1List=new ArrayList<CategoryVo>();
			//查询一级分类
			List<Category> list1=getListCategoryByLevel(1);
			//查询二级分类
			for(Category c1:list1){
				//封装一级分类
				CategoryVo categoryVo1=new CategoryVo();
				categoryVo1.setCategory(c1);
				
				//分类列表集合封装二级CategoryVo,一个CategoryVo可以取出一个Category
				List<CategoryVo> vo1ChildList=new ArrayList<CategoryVo>();
				
				//根据一级分类查询二级分类
				List<Category> list2=getListCategoryByParentId(c1.getId());
				
				//查询三级分类
				for(Category c2:list2){
					//封装二级分类
					CategoryVo categoryVo2=new CategoryVo();
					categoryVo2.setCategory(c2);
					vo1ChildList.add(categoryVo2);
					
					//分类列表集合封装三级CategoryVo,一个CategoryVo可以取出一个Category
					List<CategoryVo> vo2ChildList=new ArrayList<CategoryVo>();
					categoryVo2.setCategoryVoList(vo2ChildList);
					
					//根据二级分类查询三级分类的列表
					List<Category> list3=getListCategoryByParentId(c2.getId());
					for(Category c3:list3){
						//封装三级分类
						CategoryVo categoryVo3=new CategoryVo();
						categoryVo3.setCategory(c3);
						vo2ChildList.add(categoryVo3);
					}
				}
				categoryVo1.setCategoryVoList(vo1ChildList);
				vo1List.add(categoryVo1);			
				//将vo1List存入redis
			}
			RedisUtil.setObject("vo1List", vo1List);
			log.info("从database中取出vo1List,并存入redis");
		}
		return vo1List;
	}
	
	
}
