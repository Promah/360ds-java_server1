package com.onseo.courses.ds.admin.interfaces;

import com.onseo.courses.ds.interfaces.JsonResponseHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public interface BaseAdminUserController {

    @PutMapping("/user")
    public String addUser(@RequestHeader(name="accessToken")String token,
                          @RequestBody JsonResponseHandler.UserData data);

    @RequestMapping("/userList")
    public String getUserList(@RequestHeader(name="accessToken")String token);

}
