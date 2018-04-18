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
	public boolean addUser(User u);

	/**
	 * 删除
	 * @param 
	 * @return
	 */
	public boolean deleteUserById(int id);

	/**
	 * 修改
	 * @param 
	 * @return
	 */
	public boolean updateUser(User u);

	/**
	 * 查询
	 * @return
	 */
	public List<User> getAllUser();
	
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

	public boolean check(String type,String loginName);
	public boolean loginCheck(String loginName,String password);
	public int getTotalCount() throws SQLException;
	public List<User> findUserByPage(int pageSize,int currentPage);

}
