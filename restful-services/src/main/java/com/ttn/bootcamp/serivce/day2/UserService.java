package com.ttn.bootcamp.serivce.day2;

import com.ttn.bootcamp.entity.day2.User;
import com.ttn.bootcamp.repository.day2.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }
}
