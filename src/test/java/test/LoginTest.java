package test;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.easybuy.dao.user.UserMapper;
import com.easybuy.entity.User;
import com.easybuy.util.MyBatisUtil;

public class LoginTest {
	private static Logger logger = Logger.getLogger(Test.class);  
	
	@Test
	public static void main(String[] args) {
		SqlSession sqls=null;
		User user=new User();
		try {
			sqls=MyBatisUtil.createSqlSession();
			String name="test";
			String password="123456";
			user=sqls.getMapper(UserMapper.class).getLogin(name, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqls);
		}
		logger.debug("testLogin"+user.getLoginName()+user.getPassword());
	}

}
