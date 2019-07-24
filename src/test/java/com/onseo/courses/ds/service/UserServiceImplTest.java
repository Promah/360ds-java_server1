package com.onseo.courses.ds.service;

import com.onseo.courses.ds.entityuser.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    // rewrite or delete in the future
    @Autowired
    UserService userService;

    @Test
    public void getAllUsersTest() {
        List<User> users = userService.getAllUsers();
        Assert.isTrue(!users.isEmpty());
    }

    @Test
    public void createUser() {
    }

    @Test
    public void getUserById() {
        Optional<User> user = userService.getUserById("5d2a4dd29872ab0bfecc1c9f");
        Assert.notNull(user.get());
    }
}