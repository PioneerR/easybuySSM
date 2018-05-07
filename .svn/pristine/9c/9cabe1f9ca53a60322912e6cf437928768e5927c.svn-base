package com.easybuy.service.user;

import com.easybuy.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

	/**
	 * 增加
	 * @param 
	 * @return
	 */
	public boolean addUser(User user);

	/**
	 * 删除
	 * @param loginName
	 * @return
	 */
	public boolean deleteUserByLoginName(String loginName);

	/**
	 * 修改
	 * @param 
	 * @return
	 */
	public boolean updateUser(User user);

	/**
	 * 按用户用户查询
	 * @param 类型名称
	 * @return
	 */
	public List<User> listUserByType(String typeName );
	
	/**
	 * 查询
	 * @return
	 */
	public List<User> getAllAdmin();

	/**
	 * 根据id查询
	 * @param 
	 * @return
	 */
	public User getUserById(int id);

	/**
	 * 注册验证
	 * @param type 验证 邮箱/手机号码/注册名
	 * @param value  
	 * @return
	 */
	
	public boolean check(String type,String value);
	
	/**
	 * 登陆验证
	 * @param loginName 
	 * @param password
	 * @return
	 */
	public User loginCheck(String loginName,String password);
	/**
	 * 获取总条数
	 * @return
	 * @throws SQLException
	 */
	
	public int getTotalCount() throws SQLException;
	
	/**
	 * 分页获得用户集合
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public List<User> findUserByPage(int pageSize,int currentPage);

	public List<User> getListUser(Integer startIndex, Integer pageSize);

}
