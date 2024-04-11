package com.blog.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.payloads.JwtRequestDto;
import com.blog.api.payloads.JwtResponseDto;
import com.blog.api.payloads.UserDto;
import com.blog.api.security.JwtTokenHelper;
import com.blog.api.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
		
	@Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;
    
    @Autowired
    private UserService userService;

    
    @Autowired
    private JwtTokenHelper helper;

//    private Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody JwtRequestDto request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());
        System.out.println(request.getEmail());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());
        System.out.println("success");
        String token = this.helper.generateToken(userDetails);

        JwtResponseDto response = JwtResponseDto.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<JwtResponseDto>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            this.manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
        catch (DisabledException d) {
        	throw new DisabledException("User is disabled");
        }
    }
    
    @PostMapping("/create-user")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
    	UserDto user = this.userService.createUser(userDto);
    	
        return new ResponseEntity<UserDto>(user,HttpStatus.CREATED);
    }
    

    
}
