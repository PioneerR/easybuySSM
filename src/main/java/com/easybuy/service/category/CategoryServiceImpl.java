package com.easybuy.service.category;


import com.easybuy.dao.category.CategoryMapper;
import com.easybuy.entity.Category;
import com.easybuy.util.MyBatisUtil;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;


public class CategoryServiceImpl implements CategoryService {
	
	private List<Category> list=new ArrayList();
	boolean flag=false;
	private Logger log=Logger.getLogger("console");
	private SqlSession sqlSession=null;
	private Object obj=null;
	
	public boolean addCategory(Category c) {
		try {
			sqlSession=MyBatisUtil.createSqlSession();
			int sqlNum=sqlSession.getMapper(CategoryMapper.class).addCategory(c);
			if(sqlNum>0){
				flag=true;
			}
			sqlSession.commit();
		} catch (Exception e) {
			log.error(e);
			sqlSession.rollback();
		} finally {
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return flag;
	}

	public boolean deleteCategoryById(int id) {
		try {
			sqlSession=MyBatisUtil.createSqlSession();
			int sqlNum=sqlSession.getMapper(CategoryMapper.class).deleteCategoryById(id);
			if(sqlNum>0){
				flag=true;
			}
			sqlSession.commit();
		} catch (Exception e) {
			log.error(e);
			sqlSession.rollback();
		} finally {
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return flag;
	}

	public boolean updateCategory(Category c) {
		try {
			sqlSession=MyBatisUtil.createSqlSession();
			int sqlNum=sqlSession.getMapper(CategoryMapper.class).updateCategory(c);
			if(sqlNum>0){
				flag=true;
			}
			sqlSession.commit();
		} catch (Exception e) {
			log.error(e);
			sqlSession.rollback();
		} finally {
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return flag;
	}

	
	public Category getCategoryById(int id) {
		Category c=null;
		try {
			sqlSession=MyBatisUtil.createSqlSession();
			c=sqlSession.getMapper(CategoryMapper.class).getCategoryById(id);
			sqlSession.commit();
		} catch (Exception e) {
			log.error(e);
			sqlSession.rollback();
		} finally {
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return c;
	}
	
	public List<Category> getListCategoryByLevel(int level) {
		try {
			sqlSession=MyBatisUtil.createSqlSession();
			list=sqlSession.getMapper(CategoryMapper.class).getListCategoryByLevel(level);
		} catch (Exception e) {
			log.error(e);
		} finally {
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return list;
	}
	
	
}
