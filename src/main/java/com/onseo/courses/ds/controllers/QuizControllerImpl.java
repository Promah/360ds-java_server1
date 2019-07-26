package com.onseo.courses.ds.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onseo.courses.ds.interfaces.BaseQuizController;
import com.onseo.courses.ds.logger.Logging;
import com.onseo.courses.ds.quiz.Quiz;
import com.onseo.courses.ds.quiz.QuizResponse;
import com.onseo.courses.ds.quiz.quizSummary.QuizSummary;
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

    private List<QuizSummary> summary;
    private List<QuizResponse> answers;

    @Override
    public void getQuizList() {

    }

    @Override
    public QuizOpenResponse getOpenQuiz(String quizAssignmentID) {
        List<Quiz> quizList = null;

        try {
            quizList = getQuizListFromFile();
        }
        catch (Exception e){
            Logging.getLogger().trace("Error in quizList deserialization process in method getOpenQuiz()");
        }

        summary = new QuizSummaryController().getQuizSummaries();
        //Quiz quiz = userList.get(Integer.valueOf(quizAssignmentID));
        answers = new QuizResponseController().getQuizAnswerData();
        return new QuizOpenResponse(summary, quizList, answers);
    }

    private List<Quiz> getQuizListFromFile() throws Exception{
        Object objUser = new JSONParser().parse(new FileReader(getClass().getClassLoader().getResource("mocks/mock_quiz").getFile()));
        JSONArray array = (JSONArray) objUser;
        List<Quiz> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++){
            list.add(mapFromJson(((JSONObject)array.get(i)).toJSONString(), Quiz.class));
        }
        return list;
    }

    protected <T> T mapFromJson(String json, Class<T> tClass)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, tClass);
    }
}
