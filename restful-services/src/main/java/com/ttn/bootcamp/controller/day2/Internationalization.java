package com.ttn.bootcamp.controller.day2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/day-2/i18n")
public class Internationalization {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/greeting")
    public ResponseEntity<String> getGreeting(@RequestParam String username){
        Locale locale = LocaleContextHolder.getLocale();
        String greeting=messageSource.getMessage("greeting.message",null,"Hello",locale);
        String response = String.format("%s, %s!", greeting, username);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
