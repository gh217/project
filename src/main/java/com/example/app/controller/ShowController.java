package com.example.app.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class ShowController {

    @GetMapping
    public Object test(){
        return " show all movies";
    }


    @GetMapping(value = "/hello")
    public Object test1(){
        return " show hello";
    }


}
