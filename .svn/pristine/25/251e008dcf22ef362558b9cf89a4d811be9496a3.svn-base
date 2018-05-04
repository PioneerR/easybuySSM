package com.easybuy.dao.news;

import com.easybuy.entity.News;

import java.sql.ResultSet;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface NewsMapper {
	
	/**
	 * 增加
	 * @param 
	 * @return
	 */
	public int addNews(News news);

	/**
	 * 删除
	 * @param 
	 * @return
	 */
	public int deleteNewsById(@Param("id")int id);

	/**
	 * 修改
	 * @param 
	 * @return
	 */
	public int updateNews(News news);

	/**
	 * 查询
	 * @return
	 */
	public List<News> getAllNews();

	/**
	 * 根据id查询
	 * @param 
	 * @return
	 */
	public News getNewsById(@Param("id")int id);
}
