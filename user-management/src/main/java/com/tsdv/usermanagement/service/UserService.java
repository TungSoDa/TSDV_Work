package com.tsdv.usermanagement.service;

import com.tsdv.usermanagement.entity.User;
import com.tsdv.usermanagement.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<UserDto> getListUser();

    public UserDto getUserByID(int id);

    public List<UserDto> searchUser(String keyword);
}
