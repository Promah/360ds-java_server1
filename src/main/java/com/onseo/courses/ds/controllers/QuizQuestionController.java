package com.onseo.courses.ds.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onseo.courses.ds.quiz.QuizAnswerOption;
import com.onseo.courses.ds.quiz.QuizQuestion;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuizQuestionController {

    public List<QuizQuestion> getQuizQuestions(){

        List<QuizQuestion> quizAnswerOptions = null;

        try {
            quizAnswerOptions = getAnswersListFromFile();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return quizAnswerOptions;
    }

    private List<QuizQuestion> getAnswersListFromFile() throws Exception{
        Object objUser = new JSONParser().parse(new FileReader(getClass().getClassLoader().getResource("mocks/quiz_question_mock").getFile()));
        JSONArray array = (JSONArray) objUser;
        List<QuizQuestion> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++){
            list.add(mapFromJson(((JSONObject)array.get(i)).toJSONString(), QuizQuestion.class));
        }
        return list;
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

}
