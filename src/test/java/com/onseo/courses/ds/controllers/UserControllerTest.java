package com.onseo.courses.ds.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.FileReader;

import static org.junit.Assert.assertEquals;

public class UserControllerTest extends AbstractJsonHandler{

    private final static int HTTP_STATUS_OK                 = 200;
    private final static String ERROR_CODE_OK               = "0";
    private final static String ERROR_CODE_INVALID_REQUEST  = "-102";

    @Before
    public void init()throws Exception{
        super.setUp();
        super.setUpLoginData();
    }

    @Test
    public void authUserCorrectTest()throws Exception {
        String uri = "/api/authuser";
        String jsonRequest = "{\"uId\":\"51\",\"password\":\"pass51\"}";
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonRequest))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HTTP_STATUS_OK, status);

        String content = result.getResponse().getContentAsString();
        assertEquals(ERROR_CODE_OK, getErrorCodeFromJsonString(content));
    }

    @Test
    public void restoreSessionTest() throws Exception{
        String uriAuth= "/api/authuser";
        String uriRestore = "/api/restore_session";
        String userToken = "token";
        String jsonRequest = "{\"uId\":\"51\",\"password\":\"pass51\"}";
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post(uriAuth)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonRequest))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HTTP_STATUS_OK, status);

        String content = result.getResponse().getContentAsString();
        System.out.println(content);
        userToken = getTokenFromJsonString(content);
        System.out.println("usertoken " + userToken);

        MvcResult result1 = mvc.perform(MockMvcRequestBuilders
                .post(uriRestore)
                .header("access_token", userToken))
                .andReturn();

        int status1 = result1.getResponse().getStatus();
        assertEquals(HTTP_STATUS_OK, status1);

        assertEquals(ERROR_CODE_OK, getErrorCodeFromJsonString(content));

        String wrongToken = "wrong token";

        MvcResult result2 = mvc.perform(MockMvcRequestBuilders
                .post(uriRestore)
                .header("access_token", wrongToken))
                .andReturn();

        int status2 = result1.getResponse().getStatus();
        assertEquals(HTTP_STATUS_OK, status2);

        String content1 = result2.getResponse().getContentAsString();
        assertEquals("Invalid request", getErrorMsgFromJsonString(content1));

        assertEquals(ERROR_CODE_INVALID_REQUEST, getErrorCodeFromJsonString(content1));

    }

    @Test
    public void getStatus() throws Exception{
        String uriAuth= "/api/authuser";
        String uriStatus = "/api/status";
        String userToken = "token";
        String jsonRequest = "{\"uId\":\"51\",\"password\":\"pass51\"}";
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post(uriAuth)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonRequest))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HTTP_STATUS_OK, status);

        String content = result.getResponse().getContentAsString();
        userToken = getTokenFromJsonString(content);
        System.out.println(userToken);

        MvcResult result1 = mvc.perform(MockMvcRequestBuilders
                .get(uriStatus)
                .header("access_token", userToken))
                .andReturn();

        int status1 = result1.getResponse().getStatus();
        assertEquals(HTTP_STATUS_OK, status1);

        assertEquals(ERROR_CODE_OK, getErrorCodeFromJsonString(content));
    }

    @Test
    public void getUser(){

    }

    @Test
    public void getUserList(){

    }

}