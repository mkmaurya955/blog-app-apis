package com.blog.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.payloads.UserDto;
import com.blog.api.services.UserService;
import com.blog.api.utils.ApiResponse;

import jakarta.validation.Valid;




@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	//POST - create user
	@PostMapping("/create")
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createdUser = this.userService.createUser(userDto);
//		System.out.println();
		return new ResponseEntity<UserDto>(createdUser,HttpStatus.CREATED);
	} 
	  
	
	//PUT - update user
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDto> updateUser(@PathVariable("id") int id,@Valid @RequestBody UserDto userDto) {
		UserDto updateUser = this.userService.updateUser(userDto, id);
		
		return new ResponseEntity<UserDto>(updateUser,HttpStatus.OK);
	}
	
	//DELETE - delete user
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> deleteUser(@PathVariable("id") int id) {
		this.userService.deleteUser(id);
		return new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
	}
	
	//GET- fetch single user
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable int id) {
		UserDto userById = this.userService.getUserById(id);
		return new ResponseEntity<UserDto>(userById,HttpStatus.FOUND);
	}
	
	//GET - fetch all users
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		
		List<UserDto> allUsers = this.userService.getAllUsers();
		return  ResponseEntity.ok(allUsers);
	}
	
	
}
