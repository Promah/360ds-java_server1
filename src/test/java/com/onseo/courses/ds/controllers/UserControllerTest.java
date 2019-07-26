package com.onseo.courses.ds.controllers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.junit.Assert.*;

public class UserControllerTest extends AbstractJsonHandler{

    @Before
    public void init(){
        super.setUp();
    }

    @Test
    public void authUserCorrectTest()throws Exception {
        String uri = "/api/authuser";
        String login = "5";
        String password = "password";
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .param("uId", login)
                .param("password", password))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);

        String content = result.getResponse().getContentAsString();
        System.out.println("content " + content);
    }

    @Test
    public void restoreSessionTest() throws Exception{
        String uriAuth= "/api/authuser";
        String uriRestore = "/api/restore_session";
        String login = "5";
        String password = "password";
        String userToken = "token";
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post(uriAuth)
                .param("uId", login)
                .param("password", password))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);

        String content = result.getResponse().getContentAsString();
        System.out.println(content);
        userToken = getTokenFromJsonString(content);
        System.out.println("usertoken " + userToken);

        MvcResult result1 = mvc.perform(MockMvcRequestBuilders
                .post(uriRestore)
                .header("access_token", userToken))
                .andReturn();

        int status1 = result1.getResponse().getStatus();
        assertEquals(200, status1);

    }

    @Test
    public void getStatus() throws Exception{
        String uriAuth= "/api/authuser";
        String uriStatus = "/api/status";
        String login = "5";
        String password = "password";
        String userToken = "token";
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post(uriAuth)
                .param("uId", login)
                .param("password", password))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);

        String content = result.getResponse().getContentAsString();
        userToken = getTokenFromJsonString(content);
        System.out.println(userToken);

        MvcResult result1 = mvc.perform(MockMvcRequestBuilders
                .get(uriStatus)
                .header("access_token", userToken))
                .andReturn();

        int status1 = result1.getResponse().getStatus();
        assertEquals(200, status1);

    }

    @Test
    public void getUser(){

    }

    @Test
    public void getUserList(){

    }


    private String getTokenFromJsonString(String content) throws Exception{
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(content);
        JSONObject data = (JSONObject) jsonObject.get("data");
        return String.valueOf(data.get("access_token"));
    }

}