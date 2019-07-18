package com.onseo.courses.ds.controllers;

import com.onseo.courses.ds.models.Questions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;
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
        String login = "15";
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
        String login = "15";
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
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(content);
        userToken = (String)jsonObject.get("accessToken");
        System.out.println("usertoken " + userToken);

        MvcResult result1 = mvc.perform(MockMvcRequestBuilders
                .post(uriRestore)
                .header("accessToken", userToken))
                .andReturn();

        int status1 = result1.getResponse().getStatus();
        assertEquals(200, status1);

    }

    @Test
    public void getStatus() throws Exception{
        String uriAuth= "/api/authuser";
        String uriStatus = "/api/status";
        String login = "15";
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
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(content);
        userToken = (String)jsonObject.get("accessToken");

        MvcResult result1 = mvc.perform(MockMvcRequestBuilders
                .get(uriStatus)
                .header("accessToken", userToken))
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

}