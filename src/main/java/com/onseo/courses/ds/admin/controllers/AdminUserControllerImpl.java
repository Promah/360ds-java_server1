package com.onseo.courses.ds.admin.controllers;

import com.onseo.courses.ds.SessionTokenImpl.SessionToken;
import com.onseo.courses.ds.admin.interfaces.BaseUserController;
import com.onseo.courses.ds.entityuser.User;
import com.onseo.courses.ds.logger.Logging;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminUserControllerImpl implements BaseUserController {

    //imitation of DB
    private List<User> userList = new ArrayList<>();
    private Long ttl = 30L;

    @Override
    public String addUser(String token, User user, List<String> subordinatesIdList, List<String> managerIdList) {
        int errorCode = -1;
        String errorMessage = "";
        Map<String, Object> responseData = new LinkedHashMap<>();

        if (SessionToken.isExpired()){
            errorCode = -101;
            errorMessage = "Invalid accessToken: token time is expired";
            Logging.getLogger().warn("Error in restoreSession: token time is expired");
        }else {
            try{
                SessionToken.updateExpireTime(ttl);
                if (subordinatesIdList != null){
                    user.setSubordinates_id(subordinatesIdList);
                }
                if (managerIdList != null){
                    user.setManager_id(managerIdList);
                }

                userList.add(user);
                responseData.put("data", fillUserData(user));
                errorCode = 0;
            }catch (Exception e){
                errorCode = -102;
                errorMessage = "Invalid request";
                Logging.getLogger().error("Error in restore session: invalid request");
            }
        }

        responseData.put("errorCode", errorCode);
        responseData.put("errorMessage", errorMessage);

        JSONObject response = new JSONObject(responseData);

        return response.toJSONString();
    }

    @Override
    public String getUserList(String token) {
        int errorCode = -1;
        String errorMessage = "";
        Map<String, Object> responseData = new LinkedHashMap<>();

        if (SessionToken.isExpired()){
            errorCode = -101;
            errorMessage = "Invalid accessToken: token time is expired";
            Logging.getLogger().warn("Error in restoreSession: token time is expired");
        }else {
            try{
                SessionToken.updateExpireTime(ttl);

                responseData.put("data", userList);
                errorCode = 0;
            }catch (Exception e){
                errorCode = -102;
                errorMessage = "Invalid request";
                Logging.getLogger().error("Error in restore session: invalid request");
            }
        }
        responseData.put("errorCode", errorCode);
        responseData.put("errorMessage", errorMessage);
        JSONObject response = new JSONObject(responseData);

        return response.toJSONString();
    }

    private Map<String, Object> fillUserData(User user) {
        Map<String, Object> userMap = new LinkedHashMap<>();
        userMap.put("id", user.getId());
        userMap.put("firstName", user.getFirstName());
        userMap.put("lastName", user.getLastdName());
        userMap.put("avatar_url", user.getAvatarUrl());

        return userMap;
    }
}
