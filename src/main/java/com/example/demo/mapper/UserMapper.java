package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.RoleDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Role;
import com.example.demo.model.User;

@Component
public class UserMapper implements IMapper<User, UserDto>{
	
	@Autowired
	RoleMapper roleMapper;

	@Override
	public User toEntity(UserDto dto) {
		User entity = new User();
		BeanUtils.copyProperties(dto, entity);
		if(dto.getRoles() != null && !dto.getRoles().isEmpty()){
			List<Role> roles = new ArrayList<>();
			for(int i = 0; i < dto.getRoles().size(); i++){
				roles.add(roleMapper.toEntity(dto.getRoles().get(i)));
			}
			entity.setRoles(roles);
		}
		return entity;
	}

	@Override
	public UserDto toDto(User entity) {
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(entity, dto);
		if(entity.getRoles() != null && !entity.getRoles().isEmpty()){
			List<RoleDto> roles = new ArrayList<>();
			for(int i = 0; i < entity.getRoles().size(); i++){
				roles.add(roleMapper.toDto(entity.getRoles().get(i)));
			}
			dto.setRoles(roles);
		}
		return dto;
	}

	@Override
	public void toEntityWithNotNullProperties(UserDto dto, User entity) {
		if(dto.getUserName() != null){
			entity.setUserName(dto.getUserName());
		}
		
		if(dto.getRoles() != null && !dto.getRoles().isEmpty()){
			List<Role> roles = new ArrayList<>();
			for(int i = 0; i < dto.getRoles().size(); i++){
				roles.add(roleMapper.toEntity(dto.getRoles().get(i)));
			}
			entity.setRoles(roles);
		}
		
	}

}
