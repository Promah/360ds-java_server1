package com.onseo.courses.ds.service;

import com.onseo.courses.ds.entityuser.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAllUsers();
    public void createUser(User user);
    public Optional<User> getUserById(String id);
}
