package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;

@Service
public interface UserService extends UserDetailsService{
	UserDto findByUserName(String userName);
	
	List<UserDto> searchUser(String userName);
	
	List<UserDto> getAllUserByRole(List<Long> roleIds);
}
