package com.onseo.courses.ds.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onseo.courses.ds.interfaces.BaseQuizController;
import com.onseo.courses.ds.quiz.Quiz;
import com.onseo.courses.ds.quiz.quizSummary.QuizSummary;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static java.lang.ClassLoader.getSystemClassLoader;

@RestController
public class QuizControllerImpl implements BaseQuizController {

    private static final TypeReference<ResponseContainer<List<QuizSummary>>> RESPONSE_CONTAINER_TYPE_REFERENCE = new TypeReference<ResponseContainer<List<QuizSummary>>>() {
    };

    @Override
    public ResponseContainer<List<QuizSummary>> getQuizList() throws IOException {
        URL resource = getSystemClassLoader().getResource("mocks/mock_listQuizSummary_Valid.json");
        return new ObjectMapper().readValue(resource, RESPONSE_CONTAINER_TYPE_REFERENCE);
    }

    @Override
    public QuizOpenResponse getOpenQuiz(String quizAssignmentID) {
        Quiz quiz = new Quiz();
        return new QuizOpenResponse(quiz);
    }
}
