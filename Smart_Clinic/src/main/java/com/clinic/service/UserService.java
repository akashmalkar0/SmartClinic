package com.clinic.service;

import java.util.List;

import com.clinic.entity.*;

public interface UserService {
	void saveUser(UserDto userDto);

	User findUserByEmail(String email);

	List<UserDto> findAllUsers();
}
