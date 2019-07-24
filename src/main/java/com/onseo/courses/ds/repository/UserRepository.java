package com.onseo.courses.ds.repository;

import com.onseo.courses.ds.entityuser.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {
}
