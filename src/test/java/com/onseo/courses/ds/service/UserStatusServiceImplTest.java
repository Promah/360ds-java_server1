package com.onseo.courses.ds.service;

import com.onseo.courses.ds.models.Status;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserStatusServiceImplTest {

    @Autowired
    UserStatusService userStatusService;

    @Test
    public void getStatusByUserId() {
        Status status = userStatusService.getStatusByUserId("1");
        Assert.assertNotNull(status);
    }

    @Test
    public void saveStatus() {
//        Status status = userStatusService.getStatusByUserId("2");
        Status status = new Status();
        status.setUserId("3");
        status.setCompleteQuizCnt(10);
        status.setActiveQuizCnt(10);

        userStatusService.saveStatus(status);
    }

    @Test
    public void getStatusList() {
        List statuses =  userStatusService.getStatusList();
        Assert.assertFalse(statuses.isEmpty());
    }
}
