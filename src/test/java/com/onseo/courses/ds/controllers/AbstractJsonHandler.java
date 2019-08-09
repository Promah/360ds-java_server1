package com.onseo.courses.ds.controllers;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.onseo.courses.ds.Application;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public abstract class AbstractJsonHandler {

    protected String login;
    protected String email;
    protected String password;

    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> tClass)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, tClass);
    }

    protected String getTokenFromJsonString(String content){
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(content);
        JsonObject data = (JsonObject) jsonObject.get("data");
        return data.get("access_token").toString().replace("\"", "");
    }

    protected String getErrorMsgFromJsonString(String content){
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(content);
        return jsonObject.get("errorMessage").toString().replace("\"", "");
    }

    protected String getErrorCodeFromJsonString(String content) throws Exception{
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(content);
        return jsonObject.get("errorCode").toString();
    }

    protected String getUserLoginData() throws Exception{
        int index = 0;
        JsonArray array = (JsonArray) new JsonParser().parse(new FileReader(getClass().getClassLoader()
                .getResource("admin_request_response_files/add_user_request.json").getFile()));

        String loginData = array.get(index).getAsJsonObject().get("id").getAsString();
        loginData += " ," + array.get(index).getAsJsonObject().get("password").getAsString();
        loginData += " ," + array.get(index).getAsJsonObject().get("email").getAsString();
        return loginData;
    }

    protected void setUpLoginData() throws Exception{
        String pattern = "\\s*,\\s*";
        String[] loginData = getUserLoginData().split(pattern);
        login = loginData[0];
        password = loginData[1];
        email = loginData[2];
    }

}