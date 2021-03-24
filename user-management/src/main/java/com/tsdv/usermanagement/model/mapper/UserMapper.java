package com.tsdv.usermanagement.model.mapper;

import com.tsdv.usermanagement.entity.User;
import com.tsdv.usermanagement.model.dto.UserDto;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setAvatar(user.getAvatar());

        return dto;
    }
}
