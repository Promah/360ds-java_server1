package com.onseo.courses.ds.service;

import com.onseo.courses.ds.entytyuser.User;
import com.onseo.courses.ds.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository ;

    @Override
    public List<User> getAllUsers() {
        List users = (List) userRepository.findAll();
        return users;
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        Optional<User> user =userRepository.findById(id);
        return user;
    }
}
