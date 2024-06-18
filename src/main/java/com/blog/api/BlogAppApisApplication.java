package com.blog.api;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.blog.api.exceptions.ResourceNotFoundException;
import com.blog.api.payloads.RolesDto;
import com.blog.api.payloads.UserDto;
import com.blog.api.services.RolesServices;
import com.blog.api.services.UserService;



@SpringBootApplication
//@EnableSwagger2
//@EnableWebMvc
public class BlogAppApisApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
		

	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
//	
//	@Bean
//    public ApplicationRunner initAdminUser(UserService userService,RolesServices rolesServices) {
//        return args -> {
//        	//create roles
//        	try {
//        		RolesDto adminRole = rolesServices.getRoleById(1);
//        	}
//        	catch(ResourceNotFoundException e) {
//        		RolesDto createAdminRole = new RolesDto();
//        		createAdminRole.setId(1);
//        		createAdminRole.setRoleName("ROLE_ADMIN");
//        		rolesServices.createRole(createAdminRole);
//        	}
//        	
//        	
//        	try {
//        		RolesDto normalRole = rolesServices.getRoleById(2);
//        	}
//        	catch(ResourceNotFoundException e) {
//        		RolesDto createNormalRole = new RolesDto();
//        		createNormalRole.setId(2);
//        		createNormalRole.setRoleName("ROLE_ADMIN");
//        		rolesServices.createRole(createNormalRole);
//        	}
//        	
//        	
//        	Set<RolesDto> allRoles = new HashSet<>(rolesServices.getAllRoles());
//        	
//        	
//        	UserDto adminUser = new UserDto();
//        	
//        	
//        	adminUser.setEmail("admin@gmail.com");
//        	adminUser.setPassword("admin");
//        	adminUser.setName("admin");
//        	adminUser.setAbout("Default admin");
//        	adminUser.setRoles(allRoles);
//        	
//        	userService.createUser(adminUser);
//        };
//    }
//	
	
	

}
