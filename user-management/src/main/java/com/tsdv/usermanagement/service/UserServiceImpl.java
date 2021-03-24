package com.tsdv.usermanagement.service;

import com.tsdv.usermanagement.entity.User;
import com.tsdv.usermanagement.exception.NotFoundException;
import com.tsdv.usermanagement.model.dto.UserDto;
import com.tsdv.usermanagement.model.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserServiceImpl implements UserService {
    private static ArrayList<User> users = new ArrayList<User>();

    static {
        users.add(new User(1, "Dang Son Tung", "tungdang1999@gmail.com", "mailpassword1", "0356359859", "ava1.img"));
        users.add(new User(2, "Nguyen Thi Hang", "hangthi@gmail.com", "mailpassword2", "0123456789", "ava2.img"));
        users.add(new User(3, "Tang Thi Phuong", "phanh@gmail.com", "mailpassword3", "1234567890", "ava3.img"));
        users.add(new User(4, "Pham Trong Minh", "minhzen@gmail.com", "mailpassword4", "2345678901", "ava4.img"));
        users.add(new User(5, "Le Thi Hang", "hangsole@gmail.com", "mailpassword5", "3456789012", "ava5.img"));
        users.add(new User(6, "Nguyen Viet Duy", "duybeu@gmail.com", "mailpassword6", "4567890123", "ava6.img"));
        users.add(new User(7, "Nguyen Ngoc Son", "sonrau@gmail.com", "mailpassword7", "5678901234", "ava7.img"));
        users.add(new User(8, "Dam Van Thanh", "thanhcan@gmail.com", "mailpassword8", "6789012345", "ava8.img"));
        users.add(new User(9, "Le Viet Thinh", "thinhvachdich@gmail.com", "mailpassword9", "7890123456", "ava9.img"));
        users.add(new User(10, "Phan Duc Duy", "duyphan@gmail.com", "mailpassword10", "8901234567", "ava10.img"));
    }

    @Override
    public List<UserDto> getListUser() {
        List<UserDto> dtoList =  new ArrayList<>();
        for (User user: users) {
            dtoList.add(UserMapper.toUserDto(user));
        }
        return dtoList;
    }

    @Override
    public UserDto getUserByID(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return UserMapper.toUserDto(user);
            }
        }
        throw new NotFoundException("Cannot find user with id "+ id);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<UserDto> result = new ArrayList<>();
        for(User user : users) {
            if (user.getName().contains(keyword)) {
                result.add(UserMapper.toUserDto(user));
            }
        }
        throw new NotFoundException("Cannot find user with "+ keyword + " in name");
    }

}
