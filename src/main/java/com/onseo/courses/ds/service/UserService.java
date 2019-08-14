package com.onseo.courses.ds.service;

import com.onseo.courses.ds.entityuser.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    void createUser(User user, String password);
    User getUserByUId(String id);
}
