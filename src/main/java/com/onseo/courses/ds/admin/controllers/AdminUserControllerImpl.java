package com.onseo.courses.ds.admin.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.onseo.courses.ds.SessionTokenImpl.SessionToken;
import com.onseo.courses.ds.admin.interfaces.BaseAdminUserController;

import com.onseo.courses.ds.constants.ErrorCodes;
import com.onseo.courses.ds.db_imitation_classes.UserTable;

import com.onseo.courses.ds.entityuser.User;
import com.onseo.courses.ds.logger.Logging;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


@RestController
public class AdminUserControllerImpl implements BaseAdminUserController {


    private Long ttl = 600L;
    private final static boolean ADMIN = true;


    @Override
    public String addUser(String token, User user, List<String> subordinatesIdList, List<String> managerIdList) {
        int errorCode = -1;
        String errorMessage = "";
        JsonObject responseData = new JsonObject();

        if (SessionToken.isExpired()){
            errorCode = -101;
            errorMessage = "Invalid accessToken: token time is expired";
            Logging.getLogger().warn("Error in restoreSession: token time is expired");

        } else {
            try {
//                if (!getUserLoginFromFile().contains(email)) {
                if (!UserTable.getEmailList().contains(email)){
                    SessionToken.updateExpireTime(ttl);

                    responseData = fillUserData(user, ADMIN, email, password);
                    errorCode = ErrorCodes.STATUS_OK;
//                    writeRequestToFile(responseData);
                    UserTable.addUserData(data);


                } else {
                    errorCode = ErrorCodes.INVALID_CREDENTIALS;
                    errorMessage = "Invalid credentials: user with email " + email + " already exist";

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


    @PutMapping("/test")
    public void test(@RequestHeader(name = "access") String token,
                     @RequestBody UserData data) {
        System.out.println(token);
        System.out.println(data == null);

        System.out.println(data.getEmail());
        System.out.println(data.getPassword());

        System.out.println(data.getUser().toString());
        System.out.println(data.getUser().getSubordinatesIds().get(0));
        System.out.println(data.getUser().getManagerIds().get(0));
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

//                responseData.add("users", builder.toJsonTree(getUserDataFromFile()));
                responseData.add("users", builder.toJsonTree(UserTable.getUserDataList()));
                errorCode = ErrorCodes.STATUS_OK;
            } catch (Exception e) {
                errorCode = ErrorCodes.INVALID_REQUEST;

                errorMessage = "Invalid request";
                Logging.getLogger().error("Error in restore session: invalid request");
            }
        }
        JsonObject response = createResponseContainer(errorCode, errorMessage, responseData);
        return response.toString();
    }


    private List<String> getUserLoginFromFile() throws Exception {
//        JsonArray array = (JsonArray) new JsonParser().parse(new FileReader(getClass().getClassLoader()
//                .getResource("mocks/mock_db_users.json").getFile()));
        InputStream is = this.getClass().getResourceAsStream("/mocks/mock_db_users.json");
        String content = IOUtils.toString(is, StandardCharsets.UTF_8);
        JsonArray array = (JsonArray) new JsonParser().parse(content);
        List<String> userLoginList = new ArrayList<>();
        for (JsonElement element : array) {
            System.out.println(element.getAsJsonObject().get("email").getAsString());
            userLoginList.add(element.getAsJsonObject().get("email").getAsString());
        }
        return userLoginList;

    }


    private void writeToDBFile(JsonObject userData)throws Exception{
        System.out.println("write to db" + userData.toString());
        writeRequestToFile(userData.toString(), Objects.requireNonNull(getClass().getClassLoader()
                .getResource("mocks/mock_db_users.json")).getFile());


    }

}
