package com.onseo.courses.ds.service;

import com.onseo.courses.ds.models.Status;

import java.util.List;

public interface UserStatusService {
    Status getStatusByUserId(String userId);
    void saveStatus(Status status);
    List<Status> getStatusList();
}
