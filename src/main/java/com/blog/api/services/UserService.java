package com.blog.api.services;



import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.blog.api.payloads.UserDto;


public interface UserService  {

	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user, Integer UserId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);
	
}
