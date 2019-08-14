package com.onseo.courses.ds.admin.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.onseo.courses.ds.SessionTokenImpl.SessionToken;
import com.onseo.courses.ds.admin.interfaces.BaseAdminUserController;
import com.onseo.courses.ds.entityuser.User;
import com.onseo.courses.ds.logger.Logging;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminUserControllerImpl implements BaseAdminUserController {

    //imitation of DB
    private List<User> userList = new ArrayList<>();
    private Long ttl = 30L;

    @Override
    public String addUser(String token, User user, List<String> subordinatesIdList, List<String> managerIdList) {
        int errorCode = -1;
        String errorMessage = "";
        JsonObject responseData = new JsonObject();

        if (SessionToken.isExpired()){
            errorCode = -101;
            errorMessage = "Invalid accessToken: token time is expired";
            Logging.getLogger().warn("Error in restoreSession: token time is expired");
        }else {
            try{
                SessionToken.updateExpireTime(ttl);
                if (subordinatesIdList != null){
                    user.setSubordinatesId(subordinatesIdList);
                }
                if (managerIdList != null){
                    user.setManagerId(managerIdList);
                }

                userList.add(user);
                responseData = fillUserData(user);
                errorCode = 0;
            }catch (Exception e){
                errorCode = -102;
                errorMessage = "Invalid request";
                Logging.getLogger().error("Error in restore session: invalid request");
            }
        }

        JsonObject response = createResponseContainer(errorCode, errorMessage, responseData);

        return response.toString();
    }

    @Override
    public String getUserList(String token) {
        int errorCode = -1;
        String errorMessage = "";

        JsonObject responseData = new JsonObject();
        Gson builder = new GsonBuilder().create();
        String res = "";

        if (SessionToken.isExpired()){
            errorCode = -101;
            errorMessage = "Invalid accessToken: token time is expired";
            Logging.getLogger().warn("Error in restoreSession: token time is expired");
        }else {
            try{
                SessionToken.updateExpireTime(ttl);
                responseData.add("data", builder.toJsonTree(userList));
                errorCode = 0;
            }catch (Exception e){
                errorCode = -102;
                errorMessage = "Invalid request";
                Logging.getLogger().error("Error in restore session: invalid request");
            }
        }
        JsonObject response = createResponseContainer(errorCode, errorMessage, responseData);
        return response.toString();
    }

    private JsonObject fillUserData(User user) {
        JsonObject userData = new JsonObject();
        userData.addProperty("id", user.getId());
        userData.addProperty("firstName", user.getFirstName());
        userData.addProperty("lastName", user.getLastName());
        userData.addProperty("avatar_url", user.getAvatarUrl());

        return userData;
    }

    private JsonObject createResponseContainer(int errorCode, String errorMessage, JsonObject responseData){
        JsonObject responseContainer = new JsonObject();
        responseContainer.addProperty("errorCode", errorCode);
        responseContainer.addProperty("errorMessage", errorMessage);
        responseContainer.add("data", responseData);

        return responseContainer;
    }

}
