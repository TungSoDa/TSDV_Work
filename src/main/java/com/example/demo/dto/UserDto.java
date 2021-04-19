package com.example.demo.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	private Long userId;

	private String userName;

	private boolean enabled;

	private List<RoleDto> roles;

	public UserDto() {

	}

	@Override
	public String toString() {
		return "UserDto {userId=" + userId + ", userName=" + userName + ", roles=" + roles + "}";
	}

}
