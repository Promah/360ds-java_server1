package com.onseo.courses.ds.admin.controllers;

import com.onseo.courses.ds.controllers.AbstractJsonHandler;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

public class AdminUserControllerTest extends AbstractJsonHandler {

    private final static int HTTP_STATUS_OK                 = 200;
    private final static String ERROR_CODE_OK               = "0";
    private final static String ERROR_CODE_INVALID_REQUEST  = "-102";

    @Before
    public void init()throws Exception{
        super.setUp();
        super.setUpLoginData();
    }

    @Test
    public void addUserTest() throws Exception{

        //Authentication phase
        String uriAuth = "/api/authuser";
        String jsonRequest = "{\"uId\":\"51\",\"password\":\"pass51\"}";
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post(uriAuth)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonRequest))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HTTP_STATUS_OK, status);
        String content = result.getResponse().getContentAsString();
        assertEquals(ERROR_CODE_OK, getErrorCodeFromJsonString(content));
        String userToken = getTokenFromJsonString(content);

        //Add new user to DB phase
        String uriAdd = "/admin/user";
        String jsonAddUserRequest = "{\"user\":{\"id\":\"58\",\"firstName\":\"jim\",\"lastName\":\"stoper\",\"avatar_url\":\"urlf2\",\"subordinates_id\":[\"77\",\"22\"],\"manager_id\":[\"87\"]},\"email\":\"jim2@email\",\"password\":\"pass5858\"}";

        MvcResult result1 = mvc.perform(MockMvcRequestBuilders
                .put(uriAdd)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonAddUserRequest)
                .header("access_token", userToken))
                .andReturn();

        int status1 = result1.getResponse().getStatus();
        assertEquals(HTTP_STATUS_OK, status1);
        String content1 = result1.getResponse().getContentAsString();
        assertEquals(ERROR_CODE_OK, getErrorCodeFromJsonString(content1));
    }

    @Test
    public void getUserListTest()throws Exception{

        //Authentication phase
        String uriAuth = "/api/authuser";
        String jsonRequest = "{\"uId\":\"51\",\"password\":\"pass51\"}";
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post(uriAuth)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonRequest))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HTTP_STATUS_OK, status);
        String content = result.getResponse().getContentAsString();
        System.out.println("auth " + content);
        assertEquals(ERROR_CODE_OK, getErrorCodeFromJsonString(content));
        String userToken = getTokenFromJsonString(content);

        //List of users phase
        String uriGetList = "/admin/userList";
        MvcResult result1 = mvc.perform(MockMvcRequestBuilders
                .put(uriGetList)
                .header("access_token", userToken))
                .andReturn();

        int status1 = result1.getResponse().getStatus();
        assertEquals(HTTP_STATUS_OK, status1);
        String content1 = result1.getResponse().getContentAsString();
        System.out.println("add "+ content1);
        assertEquals(ERROR_CODE_OK, getErrorCodeFromJsonString(content1));
    }

}