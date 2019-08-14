package com.onseo.courses.ds.service;

import com.onseo.courses.ds.models.Status;
import com.onseo.courses.ds.repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserStatusServiceImpl implements UserStatusService {

    @Autowired
    UserStatusRepository repository;

    @Override
    public Status getStatusByUserId(String userId) {
        Status status = repository.findStatusByUserId(userId);
        return  status;
    }

    @Override
    public void saveStatus(Status status) {
        repository.save(status);
    }

    @Override
    public List<Status> getStatusList() {
        List<Status> statusList = (List<Status>) repository.findAll();

        return statusList;
    }
}
