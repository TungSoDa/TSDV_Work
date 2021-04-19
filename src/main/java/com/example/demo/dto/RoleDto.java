package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto {

	private Long roleId;

	private String roleName;

	public RoleDto() {

	}

	@Override
	public String toString() {
		return "RoleDto {roleId=" + roleId + ", roleName=" + roleName + "}";
	}
}
