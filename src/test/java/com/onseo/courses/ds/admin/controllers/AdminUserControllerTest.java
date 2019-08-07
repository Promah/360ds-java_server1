package com.onseo.courses.ds.admin.controllers;

import com.onseo.courses.ds.controllers.AbstractJsonHandler;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AdminUserControllerTest extends AbstractJsonHandler {

    @Before
    public void init(){
        super.setUp();
    }

    @Test
    public void addUserTest() throws Exception{

        //Authentication phase
        String uriAuth = "/api/authuser";
        String login = "5";
        String password = "password";
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post(uriAuth)
                .param("uId", login)
                .param("password", password))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        assertEquals("0", getErrorCodeFromJsonString(content));
        String userToken = getTokenFromJsonString(content);

        //Add new user to DB phase
        String uriAdd = "/admin/user";
        String id = "11";
        String firstName = "John";
        String lastName = "Nikels";
        String avatar_url = "someFaces.com";
        List<String> subordinates_id = new ArrayList<>();
        List<String> manager_id = new ArrayList<>();

        MvcResult result1 = mvc.perform(MockMvcRequestBuilders
                .put(uriAdd)
                .param("id", id)
                .param("firstName", firstName)
                .param("lastName", lastName)
                .param("avatar_url", avatar_url)
                .header("access_token", userToken))
                .andReturn();

        int status1 = result1.getResponse().getStatus();
        assertEquals(200, status1);
        String content1 = result1.getResponse().getContentAsString();
        assertEquals("0", getErrorCodeFromJsonString(content1));

    }

    @Test
    public void getUserListTest()throws Exception{

        //Authentication phase
        String uriAuth = "/api/authuser";
        String login = "5";
        String password = "password";
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post(uriAuth)
                .param("uId", login)
                .param("password", password))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        System.out.println("auth " + content);
        assertEquals("0", getErrorCodeFromJsonString(content));
        String userToken = getTokenFromJsonString(content);

        //List of users phase
        String uriGetList = "/admin/userList";
        MvcResult result1 = mvc.perform(MockMvcRequestBuilders
                .put(uriGetList)
                .header("access_token", userToken))
                .andReturn();

        int status1 = result1.getResponse().getStatus();
        assertEquals(200, status1);
        String content1 = result1.getResponse().getContentAsString();
        System.out.println("add "+ content1);
        assertEquals("0", getErrorCodeFromJsonString(content1));
    }

}