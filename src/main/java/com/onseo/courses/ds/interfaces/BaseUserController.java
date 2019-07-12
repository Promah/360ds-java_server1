package com.onseo.courses.ds.interfaces;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public interface BaseUserController {
    @PostMapping("/authuser")
    public String authUser(@RequestParam(value = "login")String login,
                           @RequestParam(value = "password")String password);


    @GetMapping("/userlist")
    public String getUserList();


    @GetMapping("/user")
    public String getUser(@RequestParam(value = "userId")String userId);
}
