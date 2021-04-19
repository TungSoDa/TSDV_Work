package com.example.demo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.demo.dto.RoleDto;
import com.example.demo.model.Role;

@Component
public class RoleMapper implements IMapper<Role, RoleDto>{

	@Override
	public Role toEntity(RoleDto dto) {
		Role entity = new Role();
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}

	@Override
	public RoleDto toDto(Role entity) {
		RoleDto dto = new RoleDto();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	@Override
	public void toEntityWithNotNullProperties(RoleDto dto, Role entity) {
		if(dto.getRoleName() != null){
			entity.setRoleName(dto.getRoleName());
		}
		
	}

}
