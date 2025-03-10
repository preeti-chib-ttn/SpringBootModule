package com.ttn.bootcamp.controller.day2;

import com.ttn.bootcamp.entity.day2.User;
import com.ttn.bootcamp.serivce.day2.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day-2/user")
@Tag(name = "Day 2 - User APIs", description = " CRUD API for user with support for xml and swagger description")

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/add",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Add user",
            description = "Add a user using xml and json.<br>Change headers to switch response and request type")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping(value = "/all",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Get all users",
            description = "Fetches all users in xml and json format.<br>Change headers to switch response and request type")
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/{username}")
    @Operation(summary = "Get user with username",
            description = "Fetches user with given username.")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @DeleteMapping("/{username}")
    @Operation(summary = "Delete user",
            description = "Delete user by given username")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return ResponseEntity.ok("User '" + username + "' deleted successfully");
    }

}
