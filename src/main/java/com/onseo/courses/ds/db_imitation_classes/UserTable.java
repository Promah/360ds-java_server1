package com.onseo.courses.ds.db_imitation_classes;

import com.onseo.courses.ds.entityuser.User;
import com.onseo.courses.ds.interfaces.JsonResponseHandler;
import com.onseo.courses.ds.logger.Logging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserTable {

    private static Map<String, JsonResponseHandler.UserData> userTable;
    private final static String defaultId = "-100";

    public UserTable() {
        if (userTable == null){
            userTable = new HashMap<>();
            JsonResponseHandler.UserData userData = createDefaultUser();
            userTable.put(userData.getUser().getId(), userData);
        }
    }

    private JsonResponseHandler.UserData createDefaultUser(){
        String email = "defaultUser";
        String password = "pass-100";
        User user = new User();
        user.setId(defaultId);
        return new JsonResponseHandler.UserData(user, email, password);
    }

    public static void addUserData(JsonResponseHandler.UserData userData){
        if (userData != null) {
            userTable.put(userData.getUser().getId(), userData);
            if (userTable.size() == 2 && getIdList().contains(defaultId)){
                userTable.remove(defaultId);
            }
        }else {
            Logging.getLogger().warn("Error while adding new user in UserTable");
        }
    }

    public static JsonResponseHandler.UserData getUserData(String id){
        return userTable.get(id);
    }

    public static List<String> getEmailList(){
        List<String> emailList = new ArrayList<>();
        userTable.values().forEach(n -> emailList.add(n.getEmail()));
        return emailList;
    }

    public static List<User> getUserList(){
        List<User> userList = new ArrayList<>();
        userTable.values().forEach(n -> userList.add(n.getUser()));
        return userList;
    }

    public static List<JsonResponseHandler.UserData> getUserDataList(){
        return new ArrayList<>(userTable.values());
    }

    public static List<String> getIdList(){
        return new ArrayList<>(userTable.keySet());
    }

}
