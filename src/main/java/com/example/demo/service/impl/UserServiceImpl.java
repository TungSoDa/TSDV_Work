package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.AppException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.MessageResource;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;
	//
	// @Autowired
	// private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getEncrytedPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	@Override
	public UserDto findByUserName(String userName) {
		return userMapper.toDto(userRepository.findByUserName(userName));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
	}

	@Override
	public List<UserDto> getAllUserByRole(List<Long> roleIds) {
		List<User> users = userRepository.getAllUserByRoles(roleIds);
		List<UserDto> userDtos = new ArrayList<>();
		if (users != null && !users.isEmpty()) {

			users.forEach(user -> userDtos.add(userMapper.toDto(user)));
		}
		return userDtos;
	}

	@Override
	public List<UserDto> searchUser(String userName) {
		try {
			List<User> users = userRepository.findByUserNameIgnoreCaseContaining(userName);
			List<UserDto> userDtos = new ArrayList<>();
			if (users != null && !users.isEmpty()) {

				users.forEach(user -> userDtos.add(userMapper.toDto(user)));
			}
			return userDtos;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR,
					MessageResource.toMessage(MessageResource.USER_SERVICE, MessageResource.GET_FAIL));
		}
	}

}
