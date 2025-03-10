package com.ttn.bootcamp.controller.day2;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/day-2/i18n")
@Tag(name = "Day 2 - Greetings", description = "Api for greeting user in different languagess")
public class I18nController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/greeting")
    public ResponseEntity<String> getGreeting(@RequestParam String username,  @RequestHeader(value = "Accept-Language", required = false) String language){
        Locale locale = LocaleContextHolder.getLocale();
        String greeting=messageSource.getMessage("greeting.message",null,"Hello",locale);
        String response = String.format("%s, %s!", greeting, username);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
