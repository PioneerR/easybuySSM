package com.easybuy.dao.user;

import org.apache.ibatis.annotations.Param;

import com.easybuy.entity.UserAddress;

public interface UserAddressMapper {
	public UserAddress getUserAddressByUserId(@Param("userId")Integer userId);
}
