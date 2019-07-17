package com.onseo.courses.ds.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onseo.courses.ds.entytyuser.User;
import com.onseo.courses.ds.interfaces.BaseUserController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.onseo.courses.ds.SessionTokenImpl.SessionToken.createToken;

@RestController
public class UserControllerImpl implements BaseUserController {


    @Override
    public String authUser(String uId, String password) {
        int userId = Integer.valueOf(uId);
        Long ttl = 360L;
        String token = "";
        JSONObject response = new JSONObject();
        try {

            List<User> userList = getUserListFromFile();
            User user = userList.get(userId);

            token = createToken(uId, ttl);
            Map userMap = new HashMap();
            userMap.put("id", user.getId());
            userMap.put("name", user.getName());
            userMap.put("email", user.getEmail());
            userMap.put("chiefId", user.getChiefId());
            userMap.put("active", user.isActive());
            userMap.put("role", user.getRole());

            Map statusMap = new HashMap();
            statusMap.put("active_quiz_cnt", 3);
            statusMap.put("complete_quiz_cnt", 5);


            response.put("accessToken", token);
            response.put("ttl", ttl);
            response.put("profile", userMap);
            response.put("status", statusMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.toJSONString();
    }

    @Override
    public String restoreSession() {
        String userId = "15";
        Long ttl = 360L;
        String token = "";
        JSONObject response = new JSONObject();
        try {
            token = createToken(userId, ttl);

            User user = getValueFromFile(Integer.valueOf(userId));
            Map userMap = new HashMap();
            userMap.put("id", user.getId());
            userMap.put("name", user.getName());
            userMap.put("email", user.getEmail());
            userMap.put("chiefId", user.getChiefId());
            userMap.put("active", user.isActive());
            userMap.put("role", user.getRole());

            Map statusMap = new HashMap();
            statusMap.put("active_quiz_cnt", 3);
            statusMap.put("complete_quiz_cnt", 5);

            response.put("accessToken", token);
            response.put("ttl", ttl);
            response.put("profile", userMap);
            response.put("status", statusMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.toJSONString();
    }

    @Override
    public String getStatus() {
        JSONObject response = new JSONObject();
        try {
            Map statusMap = new HashMap();
            statusMap.put("active_quiz_cnt", 3);
            statusMap.put("complete_quiz_cnt", 5);

            response.put("status", statusMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
