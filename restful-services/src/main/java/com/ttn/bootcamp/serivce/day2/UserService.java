package com.ttn.bootcamp.serivce.day2;

import com.ttn.bootcamp.entity.day2.User;
import com.ttn.bootcamp.exception.UserAlreadyExistsException;
import com.ttn.bootcamp.exception.UserNotFoundException;
import com.ttn.bootcamp.repository.day2.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

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
}
