package com.kunyong.lihe.service;

import com.kunyong.lihe.dto.UserDto;
import com.kunyong.lihe.model.SysUser;

public interface UserService {

	SysUser saveUser(UserDto userDto);

	SysUser updateUser(UserDto userDto);

	SysUser getUser(String username);

	void changePassword(String username, String oldPassword, String newPassword);

}
