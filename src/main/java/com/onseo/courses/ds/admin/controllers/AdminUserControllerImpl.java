package com.onseo.courses.ds.admin.controllers;

import com.google.gson.*;
import com.onseo.courses.ds.SessionTokenImpl.SessionToken;
import com.onseo.courses.ds.admin.interfaces.BaseAdminUserController;
import com.onseo.courses.ds.constants.ErrorCodes;
import com.onseo.courses.ds.entityuser.User;
import com.onseo.courses.ds.interfaces.JsonResponseHandler;
import com.onseo.courses.ds.logger.Logging;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;

@RestController
public class AdminUserControllerImpl implements BaseAdminUserController, JsonResponseHandler {

    private Long ttl = 300L;
    private final static boolean ADMIN = true;

    private final static String START_PATH = "src/main/resources/admin_request_response_files";

    @Override
    public String addUser(String token, UserData data) {
        //tmp conversion into local variables
        String email = data.getEmail();
        String password = data.getPassword();
        User user = data.getUser();

        int errorCode = -1;
        String errorMessage = "";
        JsonObject responseData = new JsonObject();

        if (SessionToken.isExpired()) {
            errorCode = ErrorCodes.INVALID_ACCESS_TOKEN;
            errorMessage = "Invalid accessToken: token time is expired";
            Logging.getLogger().warn("Error in restoreSession: token time is expired");
        } else {
            try {
                if (!getUserLoginFromFile().contains(email)) {
                    SessionToken.updateExpireTime(ttl);

                    responseData = fillUserData(user, ADMIN, email, password);
                    errorCode = ErrorCodes.STATUS_OK;
                } else {
                    errorCode = ErrorCodes.INVALID_CREDENTIALS;
                    errorMessage = "Invalid credentials: user with email " + email + " already exist";
                }
            } catch (Exception e) {
                errorCode = ErrorCodes.INVALID_REQUEST;
                errorMessage = "Invalid request";
                Logging.getLogger().error("Error in restore session: invalid request");
            }
        }

        JsonObject response = createResponseContainer(errorCode, errorMessage, responseData);

        if (errorCode == 0) {
            writeRequestToFile(new Gson().toJsonTree(data).toString(), START_PATH + "/add_user_request.json");
        }

        return response.toString();
    }

    @PutMapping("/test")
    public void test(@RequestHeader(name = "access") String token,
                     @RequestBody UserData data) {
        System.out.println(token);
        System.out.println(data == null);

//        System.out.println(data.getString());
//        System.out.println(data.getAmount());
//        System.out.println(data.getTest1() == null);

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

        if (SessionToken.isExpired()) {
            errorCode = ErrorCodes.INVALID_ACCESS_TOKEN;
            errorMessage = "Invalid accessToken: token time is expired";
            Logging.getLogger().warn("Error in restoreSession: token time is expired");
        } else {
            try {
                SessionToken.updateExpireTime(ttl);
                responseData.add("users", builder.toJsonTree(getUserDataFromFile()));
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
        JsonArray array = (JsonArray) new JsonParser().parse(new FileReader(getClass().getClassLoader()
                .getResource("mocks/mock_db_users.json").getFile()));
        //can be set /admin... instead of admin....
        List<String> userLoginList = new ArrayList<>();
        for (JsonElement element : array) {
            userLoginList.add(element.getAsJsonObject().get("email").getAsString());
        }
        System.out.println("smoes");
        return userLoginList;
    }

}
