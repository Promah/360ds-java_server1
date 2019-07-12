package com.onseo.courses.ds.controllers;

import com.onseo.courses.ds.interfaces.BaseUserController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements BaseUserController {


    @Override
    public String authUser(String login, String password) {
        return "login " + login + " password " + password;
    }

    @Override
    public String getUserList() {
        return "some userList";
    }

    @Override
    public String getUser(String userId) {
        return "userId " + userId;
    }







}
