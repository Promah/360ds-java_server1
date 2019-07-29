package com.onseo.courses.ds.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.onseo.courses.ds.SessionTokenImpl.SessionToken;
import com.onseo.courses.ds.SessionTokenImpl.TokenInfo;
import com.onseo.courses.ds.entityuser.User;
import com.onseo.courses.ds.interfaces.BaseUserController;
import com.onseo.courses.ds.logger.Logging;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static com.onseo.courses.ds.SessionTokenImpl.SessionToken.createToken;

@RestController
public class UserControllerImpl implements BaseUserController {

    private Long ttl = 30L;

    @Override
    public String authUser(String uId, String password) {
        Integer selectedId = Integer.valueOf(uId);
        System.out.println("uid " + uId + " int " + selectedId);
        String token = "";

        int activeQuizCount = 3;
        int completeQuizCount = 5;
        int errorCode = -1;
        String errorMessage = "";

        JsonObject responseGData = new JsonObject();

        try {
            List<User> userList = getUserListFromFile();

            if (selectedId < userList.size()) {
                User user = userList.get(selectedId - 1);

                token = createToken(uId, ttl);

                if (user != null) {
                    responseGData = fillResponseData(token, ttl, fillUserData(user), fillStatusData(activeQuizCount, completeQuizCount));
                    errorCode = 0;

                } else {
                    errorCode = -100;
                    errorMessage = "Invalid credentials";
                    Logging.getLogger().warn("Error in authUser: user instance equal to null");
                }
            } else {
                errorCode = -100;
                errorMessage = "Invalid credentials";
                Logging.getLogger().warn("Error in authUser: wrong id = " + selectedId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorCode = -102;
            errorMessage = "Invalid request";
            Logging.getLogger().error("Error in authUser: invalid request with id = "+ uId + ", password = " + password);
        }

        JsonObject responseG = createResponseContainer(errorCode, errorMessage, responseGData);

        return responseG.toString();
    }

    @Override
    public String restoreSession(String token) {
        int activeQuizCount = 3;
        int completeQuizCount = 5;


        int errorCode = -1;
        String errorMessage = "";

//        Map<String, Object> responseData = new LinkedHashMap<>();
        JsonObject responseData = new JsonObject();

        if (SessionToken.isExpired()) {
            errorCode = -101;
            errorMessage = "Invalid accessToken: token time is expired";
            Logging.getLogger().warn("Error in restoreSession: token time is expired");
        } else {
            try{
                SessionToken.updateExpireTime(ttl);
                TokenInfo tokenInfo = SessionToken.verifyToken(token);
                if (tokenInfo != null){
                    User user = getValueFromFile(Integer.valueOf(tokenInfo.getUserId()));
                    responseData = fillResponseData(token, ttl, fillUserData(user), fillStatusData(activeQuizCount, completeQuizCount));
                    errorCode = 0;

                } else {
                    errorCode = -101;
                    errorMessage = "Invalid user access token";
                }
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
    public String getStatus(String token) {
        int activeQuizCount = 3;
        int completeQuizCount = 5;
        int errorCode = -1;
        String errorMessage = "";

        JsonObject responseData = new JsonObject();

        if (SessionToken.isExpired()) {
            errorCode = -101;
            errorMessage = "Invalid accessToken: token time is expired";
            Logging.getLogger().warn("Error in restoreSession: token time is expired");
        } else {
            try {
                SessionToken.updateExpireTime(ttl);

                responseData =  fillStatusData(activeQuizCount, completeQuizCount);
                errorCode = 0;

            } catch (Exception e) {
                errorCode = -101;
                errorMessage = "Invalid user access token";
                Logging.getLogger().error("Error in restore session: Invalid user access token");
            }
        }
        JsonObject response = createResponseContainer(errorCode, errorMessage, responseData);

        return response.toString();
    }

    @GetMapping("/test")
    public String test()throws Exception{

        JsonParser parser = new JsonParser();
        JsonObject ggg = (JsonObject) parser.parse(new FileReader(getClass().getClassLoader().getResource("mocks/valid_auth_user.json").getFile()));
        System.out.println(ggg.toString());
        System.out.println(((JsonObject)ggg.get("data")).get("accessToken").toString());
        System.out.println(ggg.get("errorCode").toString());
        System.out.println(ggg.get("errorMessage").toString());
        return ggg.toString();
    }

    @Override
    public String getUserList() {
        return "some userList";
    }

    @Override
    public String getUser(String userId) {
        return "userId " + userId;
    }

    private JsonObject createResponseContainer(int errorCode, String errorMessage, JsonObject responseData){
        JsonObject responseContainer = new JsonObject();
        responseContainer.addProperty("errorCode", errorCode);
        responseContainer.addProperty("errorMessage", errorMessage);
        responseContainer.add("data", responseData);
        return responseContainer;
    }
    
    private JsonObject fillUserData(User user){
        JsonObject userData = new JsonObject();
        userData.addProperty("id", user.getId());
        userData.addProperty("first_name", user.getFirstName());
        userData.addProperty("last_name", user.getLastdName());
        userData.addProperty("avatar_url", user.getAvatarUrl());

        return userData;
    }

    private JsonObject fillStatusData(Integer activeQuizCount, Integer completeQuizCount){
        JsonObject statusData = new JsonObject();
        statusData.addProperty("active_quiz_cnt", activeQuizCount);
        statusData.addProperty("complete_quiz_cnt", completeQuizCount);

        return statusData;
    }

    private JsonObject fillResponseData(String token, Long ttl, JsonObject userMap, JsonObject statusMap){
        JsonObject responseData = new JsonObject();
        responseData.addProperty("access_token", token);
        responseData.addProperty("ttl", ttl);
        responseData.add("profile", userMap);
        responseData.add("status", statusMap);

        return responseData;
    }

    //tmp functionality while DB is not available

    private User getValueFromFile(Integer id)throws Exception{
        List<User> list = getUserListFromFile();
        return list.get(id - 1);
    }

    private List<User> getUserListFromFile() throws Exception{
        Object objUser = new JSONParser().parse(new FileReader(getClass().getClassLoader().getResource("mocks/mock_users.json").getFile()));
        JSONArray array = (JSONArray) objUser;
        List<User> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++){
            list.add(mapFromJson(((JSONObject)array.get(i)).toJSONString(), User.class));
        }
        return list;
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }







}
