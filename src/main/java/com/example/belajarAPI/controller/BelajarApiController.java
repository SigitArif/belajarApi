package com.example.belajarAPI.controller;

import com.example.belajarAPI.service.UserDummyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/belajar-api/v1")
public class BelajarApiController{

    @Autowired
    UserDummyService userDummyService;

@GetMapping(value = "/get")
public String belajarApi(){
    return "Mari Belajar API Baru Lagi";
}

@PostMapping(value ="/set-token")
public String setToken(){
    userDummyService.setTokens();

    return "Success";
}
}
