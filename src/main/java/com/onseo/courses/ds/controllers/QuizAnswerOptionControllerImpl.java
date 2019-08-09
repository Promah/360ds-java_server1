package com.onseo.courses.ds.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onseo.courses.ds.logger.Logging;
import com.onseo.courses.ds.quiz.QuizAnswerOption;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuizAnswerOptionControllerImpl {

    public List<QuizAnswerOption> getQuizAnswers(){

        List<QuizAnswerOption> quizAnswerOptions = null;

        try {
            quizAnswerOptions = getAnswersListFromFile();
        }
        catch (Exception e){
            Logging.getLogger().trace("Error in quizAnswerOptions deserialization process in method getQuizAnswers()");
        }

        return quizAnswerOptions;
    }


    private List<QuizAnswerOption> getAnswersListFromFile() throws Exception{
        Object objUser = new JSONParser().parse(new FileReader(getClass().getClassLoader().getResource("mocks/quiz_answer_options.json").getFile()));
        JSONArray array = (JSONArray) objUser;
        List<QuizAnswerOption> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++){
            list.add(mapFromJson(((JSONObject)array.get(i)).toJSONString(), QuizAnswerOption.class));
        }
        return list;
    }

    protected <T> T mapFromJson(String json, Class<T> tClass)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, tClass);
    }
}
