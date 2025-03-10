package com.ttn.bootcamp.controller.day2;

import com.ttn.bootcamp.entity.day2.User;
import com.ttn.bootcamp.serivce.day2.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day-2/user")
public class ContentNegotiation {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/add",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

}
