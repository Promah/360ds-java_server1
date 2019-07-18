package com.onseo.courses.ds.interfaces;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public interface BaseUserController {
    @PostMapping("/authuser")
    public String authUser(@RequestParam(value = "uId")String uId,
                           @RequestParam(value = "password")String password);



    @PostMapping("/restore_session")
    public String restoreSession();

    @GetMapping("/status")
    public String getStatus();


    @GetMapping("/userlist")
    public String getUserList();


    @GetMapping("/user")
    public String getUser(@RequestParam(value = "userId")String userId);
}
