package com.siddy.placestogo.controller;

import com.siddy.placestogo.model.Venue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping(value = "/")
    public String home(){
        return "Welcome to the Events API!";
    }

    @GetMapping(value = "/events")
    public String events(){
        return "Look at all these events!!";
    }

}
