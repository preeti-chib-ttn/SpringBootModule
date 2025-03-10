package com.ttn.bootcamp.serivce.day2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.ttn.bootcamp.entity.day2.User;
import com.ttn.bootcamp.exception.UserAlreadyExistsException;
import com.ttn.bootcamp.exception.UserNotFoundException;
import com.ttn.bootcamp.repository.day2.UserRepository;
import com.ttn.bootcamp.util.mixin.UserMixin;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    public User saveUser(User user){
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("Username '" + user.getUsername() + "' already exists!");
        }
        return userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with username: " + username));
    }

    @Transactional
    public void deleteUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UserNotFoundException("User with username '" + username + "' not found"));
        userRepository.deleteByUsername(username);
    }


    public MappingJacksonValue getUserWithDynamicFilter(List<String> fields){
        List<User> users = getAllUser();
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(new HashSet<>(fields));
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserDynamicFilter",filter);
        // dynamically add the json filter annotation
        objectMapper.addMixIn(User.class, UserMixin.class);
        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);
        return  mapping;

    }
}
