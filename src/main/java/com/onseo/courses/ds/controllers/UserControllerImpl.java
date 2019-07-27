package com.onseo.courses.ds.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onseo.courses.ds.SessionTokenImpl.SessionToken;
import com.onseo.courses.ds.SessionTokenImpl.TokenInfo;
import com.onseo.courses.ds.entityuser.User;
import com.onseo.courses.ds.interfaces.BaseUserController;
import com.onseo.courses.ds.logger.Logging;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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

        Map<String, Object> responseData = new LinkedHashMap<>();
        try {
            List<User> userList = getUserListFromFile();

            if (selectedId < userList.size()) {
                User user = userList.get(selectedId - 1);

                token = createToken(uId, ttl);

                if (user != null) {
                    responseData = fillResponseData(token, ttl, fillUserData(user), fillStatusData(activeQuizCount, completeQuizCount));
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

        JSONObject response = new JSONObject(createResponseContainer(errorCode, errorMessage, responseData));

        return response.toJSONString();
    }

    @Override
    public String restoreSession(String token) {
        int activeQuizCount = 3;
        int completeQuizCount = 5;


        int errorCode = -1;
        String errorMessage = "";

        Map<String, Object> responseData = new LinkedHashMap<>();

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

        JSONObject response = new JSONObject(createResponseContainer(errorCode, errorMessage, responseData));

        return response.toJSONString();
    }

    @Override
    public String getStatus(String token) {
        int activeQuizCount = 3;
        int completeQuizCount = 5;
        int errorCode = -1;
        String errorMessage = "";

        Map<String, Object> responseData = new LinkedHashMap<>();

        if (SessionToken.isExpired()) {
            errorCode = -101;
            errorMessage = "Invalid accessToken: token time is expired";
            Logging.getLogger().warn("Error in restoreSession: token time is expired");
        } else {
            try {
                SessionToken.updateExpireTime(ttl);

                responseData.put("status", fillStatusData(activeQuizCount, completeQuizCount));
                errorCode = 0;

            } catch (Exception e) {
                errorCode = -101;
                errorMessage = "Invalid user access token";
                Logging.getLogger().error("Error in restore session: Invalid user access token");
            }
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

    private Map<String, Object> createResponseContainer(int errorCode, String errorMessage, Map responseData){
        Map<String, Object> responseContainer = new LinkedHashMap<>();
        responseContainer.put("errorCode", errorCode);
        responseContainer.put("errorMessage", errorMessage);
        responseContainer.put("data", responseData);

        return responseContainer;
    }

    private Map<String, Object>  fillUserData(User user){
        Map<String, Object> userMap = new LinkedHashMap<>();
        userMap.put("id", user.getId());
        userMap.put("first_name", user.getFirstName());
        userMap.put("last_name", user.getLastdName());
        userMap.put("avatar_url", user.getAvatarUrl());

        return userMap;
    }

    private Map<String, Object> fillStatusData(Integer activeQuizCount, Integer completeQuizCount){
        Map<String, Object> statusMap = new LinkedHashMap<>();
        statusMap.put("active_quiz_cnt", activeQuizCount);
        statusMap.put("complete_quiz_cnt", completeQuizCount);

        return statusMap;
    }

    private Map<String, Object>  fillResponseData(String token, Long ttl, Map<String, Object> userMap, Map<String, Object> statusMap){
        Map<String, Object> responseData = new LinkedHashMap<>();
        responseData.put("access_token", token);
        responseData.put("ttl", ttl);
        responseData.put("profile", userMap);
        responseData.put("status", statusMap);

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
