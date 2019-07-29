package com.onseo.courses.ds.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.FileReader;

import static org.junit.Assert.assertEquals;

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
        assertEquals("0", getErrorCodeFromJsonString(content));
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

        assertEquals("0", getErrorCodeFromJsonString(content));

        String wrongToken = "wrong token";

        MvcResult result2 = mvc.perform(MockMvcRequestBuilders
                .post(uriRestore)
                .header("access_token", wrongToken))
                .andReturn();

        int status2 = result1.getResponse().getStatus();
        assertEquals(200, status2);

        String content1 = result2.getResponse().getContentAsString();
        assertEquals("Invalid request", getErrorMsgFromJsonString(content1));

        assertEquals("-102", getErrorCodeFromJsonString(content1));

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

        assertEquals("0", getErrorCodeFromJsonString(content));
    }

    @Test
    public void getUser(){

    }

    @Test
    public void getUserList(){

    }

    public String test()throws Exception{

        JsonParser parser = new JsonParser();
        JsonObject ggg = (JsonObject) parser.parse(new FileReader(getClass().getClassLoader().getResource("mocks/valid_auth_user.json").getFile()));
        System.out.println(ggg.toString());
        System.out.println(((JsonObject)ggg.get("data")).get("accessToken").toString());
        System.out.println(ggg.get("errorCode").toString());
        System.out.println(ggg.get("errorMessage").toString());
        return ggg.toString();
    }



}