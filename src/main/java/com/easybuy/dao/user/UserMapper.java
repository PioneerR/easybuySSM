package com.easybuy.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.easybuy.entity.User;

public interface UserMapper {
    /**
     * 登陆查询
     * @param name 账号
     * @param password 密码
     * @return User对象
     */
    User getLogin(@Param("name")String name,@Param("password")String password);
    /**
     * 查询类型用户
     * @param 类型
     * @return 用户集合
     */
  //  List<User> listUser(String type);
    /**
     * 增加用户
     * @param user  用户
     * @return 结果行数
     */
  //  int addUser(User user);
    
    /**
     * 通过登陆名删除用户
     * @param LoginName
     * @return
     */
 //   int deleteUserById(String LoginName);
    
}
