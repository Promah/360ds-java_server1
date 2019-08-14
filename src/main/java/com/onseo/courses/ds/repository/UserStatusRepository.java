package com.onseo.courses.ds.repository;

import com.onseo.courses.ds.models.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusRepository extends CrudRepository<Status,String> {
    Status findStatusByUserId(String userId);
}
