package com.onseo.courses.ds.admin.interfaces;

import com.onseo.courses.ds.entityuser.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public interface BaseUserController {

    @PutMapping("/user")
    public String addUser(@RequestHeader(name="access_token")String token, User user,
                          @RequestParam(name = "subordinates_id", required = false) List<String> subordinatesIdList,
                          @RequestParam(name = "manager_id", required = false) List<String> managerIdList);

    @RequestMapping("/userList")
    public String getUserList(@RequestHeader(name="access_token")String token);

}
