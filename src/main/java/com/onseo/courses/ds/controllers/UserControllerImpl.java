package com.onseo.courses.ds.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.onseo.courses.ds.SessionTokenImpl.SessionToken;
import com.onseo.courses.ds.SessionTokenImpl.TokenInfo;
import com.onseo.courses.ds.entityuser.User;
import com.onseo.courses.ds.interfaces.BaseUserController;
import com.onseo.courses.ds.interfaces.JsonResponseHandler;
import com.onseo.courses.ds.logger.Logging;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

import static com.onseo.courses.ds.SessionTokenImpl.SessionToken.createToken;

@RestController
public class UserControllerImpl implements BaseUserController, JsonResponseHandler {

    private Long ttl = 30L;
    //rewrite to Role Enum
    private final static boolean USER = false;

    private final static int WRONG_ID               =  0;
    private final static int STATUS_OK              =  0;
    private final static int INVALID_CREDENTIALS    = -100;
    private final static int INVALID_ACCESS_TOKEN   = -101;
    private final static int INVALID_REQUEST        = -102;

    private final static String START_PATH           = "src/main/resources/mocks/request_mocks";

    @Override
    public String authUser(String loginData) {
        Map<String, String> property = getMapFromJson(loginData);
        String uId = "none";
        String password = "none";
        int activeQuizCount = 3;
        int completeQuizCount = 5;
        int errorCode = -1;
        String errorMessage = "";

        JsonObject responseData = new JsonObject();

        try {
            uId = property.get("uId");
            password = property.get("password");
            Integer selectedId = Integer.valueOf(uId);
            System.out.println("uid " + uId + " int " + selectedId);
            String token = "";

            createJsonFileRequest(uId, password);

            List<UserData> userDataList = getUserDataFromFile();
            selectedId = isAvailable(uId, userDataList);
            if (selectedId > WRONG_ID) {

                UserData data = userDataList.get(selectedId);
                User user = data.getUser();
                System.out.println(data.getPassword());
                if (password.equals(data.getPassword())) {

                    token = createToken(uId, ttl);

                    if (user != null) {
                        responseData = fillResponseData(token, ttl,
                                fillUserData(user, USER, data.getEmail(), data.getPassword()),
                                fillStatusData(activeQuizCount, completeQuizCount));
                        errorCode = STATUS_OK;

                    } else {
                        errorCode = INVALID_CREDENTIALS;
                        errorMessage = "Invalid credentials";
                        Logging.getLogger().warn("Error in authUser: user instance equal to null");
                    }
                } else {
                    errorCode = INVALID_CREDENTIALS;
                    errorMessage = "Invalid credentials: invalid password";
                    Logging.getLogger().warn("Error in authUser: wrong password = " + selectedId);
                }
            } else {
                errorCode = INVALID_CREDENTIALS;
                errorMessage = "Invalid credentials: user id is not exists";
                Logging.getLogger().warn("Error in authUser: wrong id = " + selectedId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorCode = INVALID_REQUEST;
            errorMessage = "Invalid request";
            Logging.getLogger().error("Error in authUser: invalid request with id = "+ uId + ", password = " + password);
        }

        JsonObject response = createResponseContainer(errorCode, errorMessage, responseData);

        return response.toString();
    }

    @Override
    public String restoreSession(String token) {
        int activeQuizCount = 3;
        int completeQuizCount = 5;

        int errorCode = -1;
        String errorMessage = "";

        JsonObject responseData = new JsonObject();

        if (SessionToken.isExpired()) {
            errorCode = INVALID_ACCESS_TOKEN;
            errorMessage = "Invalid accessToken: token time is expired";
            Logging.getLogger().warn("Error in restoreSession: token time is expired");
        } else {
            try{
                SessionToken.updateExpireTime(ttl);
                TokenInfo tokenInfo = SessionToken.verifyToken(token);
                if (tokenInfo != null){
                    List<UserData> userDataList = getUserDataFromFile();

                    String userId = tokenInfo.getUserId();
                    int selectedId = isAvailable(userId, userDataList);
                    UserData data = getUserDataFromFile().get(selectedId);

                    responseData = fillResponseData(token, ttl,
                            fillUserData(data.getUser(),USER, data.getEmail(), data.getPassword()),
                            fillStatusData(activeQuizCount, completeQuizCount));
                    errorCode = STATUS_OK;

                } else {
                    errorCode = INVALID_ACCESS_TOKEN;
                    errorMessage = "Invalid user access token";
                }
            }catch (Exception e){
                errorCode = INVALID_REQUEST;
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
            errorCode = INVALID_ACCESS_TOKEN;
            errorMessage = "Invalid accessToken: token time is expired";
            Logging.getLogger().warn("Error in restoreSession: token time is expired");
        } else {
            try {
                SessionToken.updateExpireTime(ttl);

                responseData =  fillStatusData(activeQuizCount, completeQuizCount);
                errorCode = STATUS_OK;

            } catch (Exception e) {
                errorCode = INVALID_ACCESS_TOKEN;
                errorMessage = "Invalid user access token";
                Logging.getLogger().error("Error in restore session: Invalid user access token");
            }
        }
        JsonObject response = createResponseContainer(errorCode, errorMessage, responseData);

        return response.toString();
    }

    @PostMapping("/test")
    public void test(@RequestBody String loginData){
        System.out.println(loginData);
        JsonObject object = new JsonObject();
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> read = gson.fromJson(loginData, type);
        System.out.println(read.get("uId"));
        System.out.println(read.get("password"));
    }

    @Override
    public String getUserList() {
        return "some userList";
    }

    @Override
    public String getUser(String userId) {
        return "userId " + userId;
    }

    @Override
    public JsonObject fillStatusData(Integer activeQuizCount, Integer completeQuizCount){
        JsonObject statusData = new JsonObject();
        statusData.addProperty("active_quiz_cnt", activeQuizCount);
        statusData.addProperty("complete_quiz_cnt", completeQuizCount);

        return statusData;
    }

    @Override
    public JsonObject fillResponseData(String token, Long ttl, JsonObject userMap, JsonObject statusMap){
        JsonObject responseData = new JsonObject();
        responseData.addProperty("access_token", token);
        responseData.addProperty("ttl", ttl);
        responseData.add("profile", userMap);
        responseData.add("status", statusMap);

        return responseData;
    }

    private int isAvailable(String uId, List<UserData> userDataList){
        for (UserData data : userDataList){
            if (uId.equals(data.getUser().getId())){
                return userDataList.indexOf(data);
            }
        }
        return WRONG_ID;
    }

    private void createJsonFileRequest(String uId, String password) {
        writeRequestToFile(createJsonRequest(Arrays.asList("uId", "password"), Arrays.asList(uId, password)),
                START_PATH + "/auth_user_request_mock.json");

    }

    //tmp functionality while DB is not available

    private User getValueFromFile(Integer id)throws Exception{
        List<User> list = getUserListFromFile();
        return list.get(id - 1);
    }

    private List<User> getUserListFromFile() throws Exception{
        Object objUser = new JSONParser().parse(new FileReader(getClass().getClassLoader().getResource("mocks/mock_users.json").getPath()));
        JSONArray array = (JSONArray) objUser;
        List<User> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++){
            list.add(mapFromJson(((JSONObject)array.get(i)).toJSONString(), User.class));
        }
        return list;
    }

    protected <T> T mapFromJson(String json, Class<T> tClass)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, tClass);
    }
}
