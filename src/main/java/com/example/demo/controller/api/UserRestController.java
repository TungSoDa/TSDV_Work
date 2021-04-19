package com.example.demo.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotation.Timed;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import com.example.demo.util.MessageResource;
import com.example.demo.util.ResponseEntity;



@RestController
@RequestMapping("/admin/userAPI")
public class UserRestController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	@Timed
	public ResponseEntity<List<UserDto>> searchUser(@RequestParam("userName") String userName) {

		List<UserDto> userDtos = userService.searchUser(userName);
		
		if (userDtos != null) {
			return new ResponseEntity<>(userDtos, HttpStatus.OK,
					MessageResource.toMessage(MessageResource.ADMIN_USERAPI, MessageResource.GET_SUCCESS), userName);
		} else {
			return new ResponseEntity<>(userDtos, HttpStatus.NOT_FOUND,
					MessageResource.toMessage(MessageResource.ADMIN_USERAPI, MessageResource.GET_NO_DATA), userName);
		}
	}
}
