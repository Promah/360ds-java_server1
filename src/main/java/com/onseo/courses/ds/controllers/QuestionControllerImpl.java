package com.onseo.courses.ds.controllers;

import com.onseo.courses.ds.interfaces.BaseQuestionController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionControllerImpl implements BaseQuestionController {

    @Override
    public String getQuestions() {
        return "questions";
    }

    @Override
    public String getAnswers(String user_id, String question_id, String answers_id) {
        return "answers";
    }

    @Override
    public String getSummary() {
        return "summary";
    }

    @Override
    public TemporaryClassResponse getResults(String user_id) {
        return new TemporaryClassResponse(user_id);
    }
}
