package com.onseo.courses.ds.controllers;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onseo.courses.ds.interfaces.BaseQuizController;
import com.onseo.courses.ds.logger.Logging;
import com.onseo.courses.ds.quiz.Quiz;
import com.onseo.courses.ds.quiz.quizSummary.QuizSummary;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URL;
import static java.lang.ClassLoader.getSystemClassLoader;
import com.onseo.courses.ds.quiz.QuizResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class QuizControllerImpl implements BaseQuizController {

    private List<QuizSummary> summary;
    private List<QuizResponse> answers;
    private static final TypeReference<ResponseContainer<List<QuizSummary>>> RESPONSE_CONTAINER_TYPE_REFERENCE = new TypeReference<ResponseContainer<List<QuizSummary>>>() {
    };

    @Override
    public ResponseContainer<List<QuizSummary>> getQuizList() throws IOException {
        URL resource = getSystemClassLoader().getResource("mocks/mock_listQuizSummary_Valid.json");
        return new ObjectMapper().readValue(resource, RESPONSE_CONTAINER_TYPE_REFERENCE);
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
        Object objUser = new JSONParser().parse(new FileReader(Objects.requireNonNull(getClass().getClassLoader().getResource("mocks/mock_quiz.json")).getFile()));
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

    @Override
    public Object postQuizSubmit(String quizAssignmentID) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(new FileReader(Objects.requireNonNull(getClass().getClassLoader().getResource("mocks/mock_quiz_submit_valid.json")).getFile()));
    }


}
