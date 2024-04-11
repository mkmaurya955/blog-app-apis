package com.blog.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.api.entity.User;
import com.blog.api.exceptions.ResourceNotFoundException;
import com.blog.api.payloads.UserDto;
import com.blog.api.repositories.UserRepo;
import com.blog.api.services.UserService;
import com.blog.api.utils.CurrentDateTime;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CurrentDateTime currentDateTime;
	
	@Autowired
	private ModelMapper modelMapper;
		
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = userDtoToUser(userDto);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setCreationTime(currentDateTime.getDateTime());
		User savedUser = this.userRepo.save(user);
		return this.userToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "id", userId)); 
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setAbout(userDto.getAbout());
		user.setEditionTime(currentDateTime.getDateTime());
		User updatedUser = this.userRepo.save(user);
		
		return this.userToUserDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->(new ResourceNotFoundException("user", "id", userId)));
		return this.userToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> allUsers = this.userRepo.findAll();
		List<UserDto> allUserDtos = allUsers.stream().map(user->this.userToUserDto(user)).collect(Collectors.toList());
		return allUserDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
//		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
//		this.userRepo.delete(user);
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "id", userId)); 
		user.setDeletedAt(currentDateTime.getDateTime());
		this.userRepo.save(user);
		
	}
	
	private User userDtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
	}
	private UserDto userToUserDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		return userDto;
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User userByEmail = this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("username", "email",username));
//		return userByEmail;
//	}

}
