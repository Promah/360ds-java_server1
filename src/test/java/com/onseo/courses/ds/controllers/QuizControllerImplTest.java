package com.onseo.courses.ds.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(QuizControllerImpl.class)
public class QuizControllerImplTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getQuizListTest() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .get("/api/quiz/list")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getOpenQuizTest() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .get("/api/quiz/open/{quizAssignmentID}", "assignment_ID")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void postQuizSubmit() throws Exception {
        String url = "/api/quiz/submit/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        int resultStatus = mvcResult.getResponse().getStatus();
        assertEquals(200,resultStatus);
    }
}
