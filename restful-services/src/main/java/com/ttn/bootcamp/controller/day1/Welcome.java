package com.ttn.bootcamp.controller.day1;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Welcome {

    @GetMapping("/welcome")
    @ResponseBody
    public ResponseEntity<String> getGreetings(){
        return new ResponseEntity<>("Welcome to Spring Boot", HttpStatus.OK);
    }
}
