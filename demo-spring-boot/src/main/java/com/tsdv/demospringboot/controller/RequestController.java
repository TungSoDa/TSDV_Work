package com.tsdv.demospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestController {
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello(@RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {
        // Request param "name" sẽ được gán giá trị vào biến String
        // Model là một object của Spring Boot, được gắn vào trong mọi request
        // Gắn vào model giá trị name nhận được
        model.addAttribute("name", name);
        model.addAttribute("programmingLanguage", "Java Spring");
        return "hello";
    }

    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

//    @RequestMapping(value = "userInfo", method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseEntity<?> userInfo() {
//        User user = User.builder().id(121).name("Dang Son Tung").build();
//        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(user);
//    }

    @RequestMapping(value = "userInfo", method = RequestMethod.GET)
    public String userInfo2(User user, Model model) {
        user.setId(121);
        user.setName("Dang Son Tung");
        model.addAttribute("user", user);
        return "userInfo";
    }
}
