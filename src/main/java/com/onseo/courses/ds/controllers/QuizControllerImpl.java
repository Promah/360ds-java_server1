package com.onseo.courses.ds.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onseo.courses.ds.interfaces.BaseQuizController;
import com.onseo.courses.ds.quiz.Quiz;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class QuizControllerImpl implements BaseQuizController {

    @Override
    public void getQuizList() {

    }

    @Override
    public QuizOpenResponse getOpenQuiz(String quizAssignmentID) {
        List<Quiz> userList = null;

        try {
            userList = getUserListFromFile();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(Integer.valueOf(quizAssignmentID));
        Quiz quiz = userList.get(Integer.valueOf(quizAssignmentID));
        return new QuizOpenResponse(quiz);
    }

    private List<Quiz> getUserListFromFile() throws Exception{
        Object objUser = new JSONParser().parse(new FileReader(getClass().getClassLoader().getResource("mocks/mock_quiz").getFile()));
        JSONArray array = (JSONArray) objUser;
        List<Quiz> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++){
            list.add(mapFromJson(((JSONObject)array.get(i)).toJSONString(), Quiz.class));
        }
        return list;
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}
