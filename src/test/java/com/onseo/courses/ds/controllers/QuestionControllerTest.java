package com.onseo.courses.ds.controllers;

import com.onseo.courses.ds.models.Questions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QuestionControllerTest extends AbstractJsonHandler {

    @Before
    public void init(){
        super.setUp();
    }

    @Test
    public void getQuestionsTest()throws Exception{
        String uri = "/api/questions";
        String userToken = "token";
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .header("Content-type", "application/json")
                .header("accessToken", userToken))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);

        String content = result.getResponse().getContentAsString();
        List<Questions> responseList = super.mapFromJson(content, List.class);
        assertTrue(responseList.size() > 0);
    }

    @Test
    public void getAnswersTest() throws Exception{
        String uri = "/api/answers";
        String userToken = "token";
        String userId = "5";
        String questionId = "12";
        String answersId = "31";

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get(uri)
                .header("Content-type", "application/json")
                .header("accessToken", userToken)
                .param("userId", userId)
                .param("questionId", questionId)
                .param("answerIds", answersId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response", is("5 12 31")))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);

    }

    @Test
    public void getSummaryTest() throws Exception{
        String uri = "/api/summary";
        String userToken = "token";
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get(uri)
                .header("Content-type", "application/json")
                .header("accessToken", userToken))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);

        String content = result.getResponse().getContentAsString();
        List<TemporaryClassResponse> responseList = super.mapFromJson(content, List.class);
        assertTrue(responseList.size() > 0);
    }

    @Test
    public void getResultsTest() throws Exception{
        String uri = "/api/results";
        String userId = "5";
        String userToken = "token";
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get(uri)
                .param("userId", userId)
                .header("Content-type", "application/json")
                .header("accessToken", userToken))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);

        String content = result.getResponse().getContentAsString();
        List<TemporaryClassResponse> responseList = super.mapFromJson(content, List.class);
        System.out.println(responseList.size());
        assertTrue(responseList.size() > 0);

//        System.out.println("length " + responses.length);
//        for (TemporaryClassResponse response : responses){
//            System.out.println(response.getResponse());
//        }


    }


}
