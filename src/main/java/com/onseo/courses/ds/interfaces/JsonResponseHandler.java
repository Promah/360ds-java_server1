package com.onseo.courses.ds.interfaces;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.onseo.courses.ds.entityuser.User;
import com.onseo.courses.ds.logger.Logging;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface JsonResponseHandler {

    default JsonObject createResponseContainer(int errorCode, String errorMessage, JsonObject responseData) {
        JsonObject responseContainer = new JsonObject();
        responseContainer.addProperty("errorCode", errorCode);
        responseContainer.addProperty("errorMessage", errorMessage);
        responseContainer.add("data", responseData);

        return responseContainer;
    }

    default JsonObject fillUserData(User user, boolean isAdmin, String email, String password) {
        Gson gson = new Gson();
        JsonObject userData = new JsonObject();
        userData.addProperty("id", user.getId());
        userData.addProperty("firstName", user.getFirstName());
        userData.addProperty("lastName", user.getLastdName());
        userData.addProperty("avatar_url", user.getAvatarUrl());
        userData.add("subordinates_id", gson.toJsonTree(user.getSubordinatesIds()));
        userData.add("manager_id", gson.toJsonTree(user.getManagerIds()));
        userData.addProperty("email", email);
        if (isAdmin) {
            userData.addProperty("password", password);
        }

        return userData;
    }

    default JsonObject fillStatusData(Integer activeQuizCount, Integer completeQuizCount){
        return null;
    }

    default JsonObject fillResponseData(String token, Long ttl, JsonObject userMap, JsonObject statusMap){
        return null;
    }

    default List<UserData> getUserDataFromFile()throws Exception{
//        JsonArray array = (JsonArray) new JsonParser().parse(new FileReader(getClass().getClassLoader()
//                .getResource("mocks/mock_db_users.json").getFile()));
        InputStream is = this.getClass().getResourceAsStream("/mocks/mock_db_users.json");
        String content = IOUtils.toString(is, StandardCharsets.UTF_8);
        System.out.println("content " + content);
        JsonArray array = (JsonArray) new JsonParser().parse(content);
        List<UserData> userData = new ArrayList<>();
        for (JsonElement element : array){
            User userInfo = new User();
            userInfo.setId(element.getAsJsonObject().get("id").getAsString());
            userInfo.setFirstName(element.getAsJsonObject().get("firstName").getAsString());
            userInfo.setLastName(element.getAsJsonObject().get("lastName").getAsString());
            userInfo.setAvatar_url(element.getAsJsonObject().get("avatar_url").getAsString());
            userInfo.setSubordinates_id(getListFromJson(element.getAsJsonObject().get("subordinates_id").getAsJsonArray()));
            userInfo.setManager_id(getListFromJson(element.getAsJsonObject().get("manager_id").getAsJsonArray()));

            userData.add(new UserData(userInfo,
                    element.getAsJsonObject().get("email").getAsString(),
                    element.getAsJsonObject().get("password").getAsString()));

        }
        return userData;
    }

    default List<String> getListFromJson(JsonArray array){
        List<String> values = new ArrayList<>();
        for (JsonElement element : array){
            values.add(element.getAsString());
        }
        return values;
    }

    class UserData {

        private User user;
        private String email;
        private String password;

        public UserData(User userData, String email, String password) {
            this.user = userData;
            this.email = email;
            this.password = password;
        }

        public User getUser() {
            return user;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }

    default void writeRequestToFile(String json, String path){
        File file = new File(path);
        try(RandomAccessFile writer = new RandomAccessFile(file, "rw")){
            long fileLength = file.length();
            int offset = 1;
            if (fileLength > 0){
                json = "," + json + "]";
                writer.seek(fileLength - offset);
                writer.writeBytes(json);
            } else {
                json = "[" + json + "]";
                writer.writeBytes(json);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    default String createJsonRequest(List<String> property, List<String> value){
        JsonObject jsonObject = new JsonObject();
        int propSize = property.size();
        int valueSize = value.size();
        if (propSize == valueSize){
            for (int i = 0; i < propSize; i++){
                jsonObject.addProperty(property.get(i), value.get(i));
            }

        }else {
            Logging.getLogger().warn("Error in JsonResponseHandler: prop size not equal to values size");
        }
        return jsonObject.toString();
    }
    
    default Map<String, String> getMapFromJson(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> property = gson.fromJson(json, type);
        
        return property;
    }
}
