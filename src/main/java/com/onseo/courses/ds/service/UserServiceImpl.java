package com.onseo.courses.ds.service;

import com.onseo.courses.ds.entityuser.User;
import com.onseo.courses.ds.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public List<User> getAllUsers() {
        List<User> users = (List<User>) repository.findAll();
        return users;
    }

    @Override
    public void createUser(User user, String password) {
        DbUser dbUser = new DbUser(password, user);
        repository.save(dbUser);

    }

    @Override
    public User getUserByUId(String id) {
        User user = repository.findByUId(id);
        return user;
    }

    @Document(collection = "users")
    private class DbUser extends User{
        @Field("password")
        private String pass;

        public DbUser(String pass, User user) {
            super(user);
            this.pass = pass;
        }
    }
}
