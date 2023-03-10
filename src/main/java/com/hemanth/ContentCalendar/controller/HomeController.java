package com.hemanth.ContentCalendar.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @Value("${cc.welcome-message}")
    private  String welcomeMessage;
    @Value("${cc.about}")
    private String about;
    @GetMapping("/")
    public Map<String, String> home(){
        return Map.of("welcomeMessage", welcomeMessage, "about",about);
    }
}
