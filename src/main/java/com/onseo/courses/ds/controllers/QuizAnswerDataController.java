package com.onseo.courses.ds.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onseo.courses.ds.logger.Logging;
import com.onseo.courses.ds.quiz.QuizAnswerData;
import com.onseo.courses.ds.quiz.QuizQuestion;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuizAnswerDataController {

    public List<QuizAnswerData> getQuizAnswerData(){

        List<QuizAnswerData> quizAnswerDataList = null;

        try {
            quizAnswerDataList = getAnswersListFromFile();
        }
        catch (Exception e){
            Logging.getLogger().trace("Error in quizAnswerDataList deserialization process in method getQuizAnswerData()");
        }

        return quizAnswerDataList;
    }

    private List<QuizAnswerData> getAnswersListFromFile() throws Exception{
        Object objUser = new JSONParser().parse(new FileReader(getClass().getClassLoader().getResource("mocks/quiz_answer_data_mock").getFile()));
        JSONArray array = (JSONArray) objUser;
        List<QuizAnswerData> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++){
            list.add(mapFromJson(((JSONObject)array.get(i)).toJSONString(), QuizAnswerData.class));
        }
        return list;
    }

    protected <T> T mapFromJson(String json, Class<T> tClass)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, tClass);
    }

}
