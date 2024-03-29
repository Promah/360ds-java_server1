package com.onseo.courses.ds.interfaces;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public interface BaseUserController {
    @PostMapping("/authuser")
    public String authUser(@RequestBody String loginData);

    @PostMapping("/restore_session")
    public String restoreSession(@RequestHeader(name = "accessToken") String token);

    @GetMapping("/status")
    public String getStatus(@RequestHeader(name = "accessToken") String token);

    @GetMapping("/userlist")
    public String getUserList();

    @GetMapping("/user")
    public String getUser(@RequestParam(value = "userId")String userId);
}
