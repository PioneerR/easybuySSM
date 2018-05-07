package com.easybuy.service.user;

import com.easybuy.dao.user.UserAddressMapper;
import com.easybuy.entity.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAddressService")
public class UserAddressServiceImpl implements UserAddressService {
	@Autowired(required=false)
	private UserAddressMapper userAddressMapper;
	@Override
	public UserAddress getUserAddressByUserId(int userId) {
		UserAddress userAddress =userAddressMapper.getUserAddressByUserId(userId);
		return userAddress;
	}
	
}
