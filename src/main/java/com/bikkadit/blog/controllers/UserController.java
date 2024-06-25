package com.bikkadit.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikkadit.blog.payloads.UserDto;
import com.bikkadit.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
   private UserService userService;
	
	//	POST- Create user
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		
		UserDto createUserDto = this.userService.createUser(userDto);
		
		return new ResponseEntity<>(createUserDto , HttpStatus.CREATED);
		
	}
	
	//  PUT- Update user
	
	//  DELETE- Delete user
	
	//  GET - user get
	

}
