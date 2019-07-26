package com.onseo.courses.ds.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onseo.courses.ds.SessionTokenImpl.SessionToken;
import com.onseo.courses.ds.entityuser.User;
import com.onseo.courses.ds.interfaces.BaseUserController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static com.onseo.courses.ds.SessionTokenImpl.SessionToken.createToken;

@RestController
public class UserControllerImpl implements BaseUserController {

    private Long ttl = 20L;
    private Integer selectedId;

    @Override
    public String authUser(String uId, String password) {
        selectedId = Integer.valueOf(uId);
        System.out.println("uid " + uId + " int " + selectedId);
        String token = "";

        int activeQuizCount = 3;
        int completeQuizCount = 5;
        int errorCode = -1;
        String errorMessage = "";

        Map userMap = new LinkedHashMap();
        Map statusMap = new LinkedHashMap();
        Map responseData = new LinkedHashMap();
        try {
            List<User> userList = getUserListFromFile();



            if (selectedId < userList.size()) {
                User user = userList.get(selectedId - 1);

                token = createToken(uId, ttl);

                if (user != null) {
                    userMap.put("id", user.getId());
                    userMap.put("first_name", user.getFirstName());
                    userMap.put("last_name", user.getLastdName());
                    userMap.put("avatar_url", user.getAvatarUrl());

                    statusMap.put("active_quiz_cnt", activeQuizCount);
                    statusMap.put("complete_quiz_cnt", completeQuizCount);

                    responseData.put("access_token", token);
                    responseData.put("ttl", ttl);
                    responseData.put("profile", userMap);
                    responseData.put("status", statusMap);
                    errorCode = 0;

                } else {
                    errorCode = -100;
                    errorMessage = "Invalid credentials";
                }
            } else {
                errorCode = -100;
                errorMessage = "Invalid credentials";
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorCode = -100;
            errorMessage = "Invalid credentials";

        }

        JSONObject response = new JSONObject(createResponseContainer(errorCode, errorMessage, responseData));

        return response.toJSONString();
    }

    @Override
    public String restoreSession(String token) {
        int activeQuizCount = 3;
        int completeQuizCount = 5;


        int errorCode = -1;
        String errorMessage = "";

        Map userMap = new LinkedHashMap();
        Map statusMap = new LinkedHashMap();
        Map responseData = new LinkedHashMap();
        if (selectedId != null) {
            try {
                if (SessionToken.isValid(token)) {
                    if (SessionToken.isExpired()) {
//                      throw new TokenExpiredException("Token time is expired. Please authorise again.");
                        errorCode = -101;
                        errorMessage = "Invalid accessToken: token time is expired";
                    } else {
                        SessionToken.updateExpireTime(ttl);
                        User user = getValueFromFile(selectedId);

                        userMap.put("id", user.getId());
                        userMap.put("first_name", user.getFirstName());
                        userMap.put("last_name", user.getLastdName());
                        userMap.put("avatar_url", user.getAvatarUrl());

                        statusMap.put("active_quiz_cnt", activeQuizCount);
                        statusMap.put("complete_quiz_cnt", completeQuizCount);

                        responseData.put("access_token", token);
                        responseData.put("ttl", ttl);
                        responseData.put("profile", userMap);
                        responseData.put("status", statusMap);

                        errorCode = 0;
                    }
                }else {
                    errorCode = -101;
                    errorMessage = "Invalid user access token";
                }
            } catch (Exception e) {
                e.printStackTrace();
                errorCode = -101;
                errorMessage = "Invalid user access token";
            }
        } else {
            errorCode = -102;
            errorMessage = "Invalid request. Please log in system and retry.";
        }
        JSONObject response = new JSONObject(createResponseContainer(errorCode, errorMessage, responseData));

        return response.toJSONString();
    }

    @Override
    public String getStatus(String token) {
        int activeQuizCount = 3;
        int completeQuizCount = 5;
        int errorCode = -1;
        String errorMessage = "";

        Map responseData = new LinkedHashMap();

        try {
            if (SessionToken.isValid(token)) {
                if (SessionToken.isExpired()) {
//                    throw new TokenExpiredException("Token time is expired. Please authorise again.");
                    errorCode = -101;
                    errorMessage = "Invalid user access token: token time is expired";

                } else {

                    SessionToken.updateExpireTime(ttl);
                    Map statusMap = new HashMap();
                    statusMap.put("active_quiz_cnt", activeQuizCount);
                    statusMap.put("complete_quiz_cnt", completeQuizCount);

                    responseData.put("status", statusMap);
                    errorCode = 0;
                }
            } else {
                errorCode = -101;
                errorMessage = "Invalid user access token";
            }
        }catch (Exception e){
            e.printStackTrace();
            errorCode = -101;
            errorMessage = "Invalid user access token";
        }
        JSONObject response = new JSONObject(createResponseContainer(errorCode, errorMessage, responseData));

        return response.toJSONString();
    }

    @Override
    public String getUserList() {
        return "some userList";
    }

    @Override
    public String getUser(String userId) {
        return "userId " + userId;
    }

    private Map createResponseContainer(int errorCode, String errorMessage, Map responseData){
        Map responseContainer = new LinkedHashMap();
        responseContainer.put("errorCode", errorCode);
        responseContainer.put("errorMessage", errorMessage);
        responseContainer.put("data", responseData);

        return responseContainer;
    }


    //tmp functionality while DB is not available

    private User getValueFromFile(int id)throws Exception{
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
