package com.onseo.courses.ds.service;

import com.onseo.courses.ds.entityuser.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


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
        Assert.assertTrue(!users.isEmpty());
    }

    @Test
    public void createUser() {
        User user = new User();
        user.setFirstName("test");
        user.setLastName("test");
        userService.createUser(user, "password");
    }

    @Test
    public void getUserById() {
        User user = userService.getUserByUId("1");
        Assert.assertNotNull(user);
    }
}