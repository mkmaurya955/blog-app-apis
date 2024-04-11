package com.blog.api.payloads;

import java.util.Set;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	
	private int id;
	
	@NotEmpty(message = "Name is mandatory")
	@Size(min= 3, message="Please enter valid name")
	private String name;
	
	@NotEmpty(message = "Email is mandatory")
	@Email(message = "Email should be valid")
	private String email;
	
	@JsonIgnore
	@NotEmpty(message = "Password is mandatory")
	@Size(min = 6, max = 15, message = "Password must be min:6 character or max: 15 characters")
	private String password;
	
	private String about;
	
	private Set<RoleDto> roles;
	
}
