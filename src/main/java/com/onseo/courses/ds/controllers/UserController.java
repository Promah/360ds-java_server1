package com.onseo.courses.ds.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @PostMapping("/api/authuser")
    public String authUser(@RequestParam(value = "login", required = true)String login,
                           @RequestParam(value = "password", required = true)String password){


        return "login " + login + " password " + password;
    }


    @GetMapping("/api/userlist")
    public String getUserlist(){


        return "some userlist";
    }


    @GetMapping("/api/user")
    public String getUser(@RequestParam(value = "userid", required = true)String userid){


        return "userid " + userid;
    }


}
