package com.onseo.courses.ds.repository;

import com.onseo.courses.ds.entytyuser.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {
}
