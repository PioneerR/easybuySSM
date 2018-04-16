package com.easybuy.util;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

public class MyBatisUtil {
	
	private static Logger log=Logger.getLogger("mylog");
	private MyBatisUtil(){}
	
	private static ThreadLocal<SqlSession> threadLocal=new ThreadLocal<SqlSession>();
	private static SqlSessionFactory factory;
	
	static{
		try {
			InputStream is=Resources.getResourceAsStream("mybatis-config.xml");
			factory=new SqlSessionFactoryBuilder().build(is);
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	public static SqlSession createSqlSession() {
		SqlSession sqlSession=threadLocal.get();
		if(sqlSession==null){
			//手动提交事务管理，sqlSession=factory.openSession(false);
			//默认为true,就是自动提交事务，更多地采手动提交：比较安全
			sqlSession=factory.openSession(false);
			threadLocal.set(sqlSession);
		}		
		return sqlSession;
	}
	
	public static void closeSqlSession(SqlSession sqlSession) {
		if(sqlSession!=null){
			sqlSession.close();
			threadLocal.remove();			
		}
	}
	
//  每次创建MybatisUtil都测试一下是否连接成功?
//	public static void main(String[] args) {		
//		Connection conn=MyBatisUtil.createSqlSession().getConnection();
//		System.out.println(conn==null?"连接失败":"连接成功");		
//	}
	
}
