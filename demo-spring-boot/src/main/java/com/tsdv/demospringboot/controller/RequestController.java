package com.tsdv.demospringboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestController {
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "userInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> userInfo() {
        User user = User.builder().ID(121).name("DangSonTung").build();
//        User user = new User("OJT_121", "DangSonTung");
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(user);
    }
}
