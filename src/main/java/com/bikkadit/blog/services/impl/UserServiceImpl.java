package com.bikkadit.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikkadit.blog.entities.User;
import com.bikkadit.blog.exceptions.ResourceNotFoundException;
import com.bikkadit.blog.payloads.UserDto;
import com.bikkadit.blog.repositories.UserRepository;
import com.bikkadit.blog.services.UserService;

@Service
public  class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = this.dtotoUser(userDto);
		User savedUser = this.userRepository.save(user);

		return this.usertoDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user2 = this.userRepository.findById(userId)
		.orElseThrow(()-> new ResourceNotFoundException("user" , "Id" ,userId));
		
		user2.setName(userDto.getName());
		user2.setEmail(userDto.getEmail());
		user2.setPass(userDto.getPass());
		user2.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepository.save(user2);
		
		UserDto userDto1 = this.usertoDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user=this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "Id", userId));
		return this.usertoDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users = this.userRepository.findAll();
		List<UserDto> userDtos = users.stream().map(user ->this.usertoDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer UserId) {
		
		User user= this.userRepository.findById(UserId)
		.orElseThrow(() -> new ResourceNotFoundException("User", "Id", UserId));
		
		this.userRepository.delete(user);

	}

	public User dtotoUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPass(userDto.getPass());
//		user.setAbout(userDto.getAbout());

		return user;

	}

	public UserDto usertoDto(User user) {

		UserDto userdto = this.modelMapper.map(user , UserDto.class);
		
//		userdto.setId(user.getId());
//		userdto.setName(user.getName());
//		userdto.setEmail(user.getEmail());
//		userdto.setPass(user.getPass());
//		userdto.setAbout(user.getAbout());

		return userdto;

	}

}
