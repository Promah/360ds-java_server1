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
    public String getAnswers(Integer user_id, Integer question_id, Integer[] answers_id) {
        return "answers";
    }

    @Override
    public String getSummary() {
        return "summary";
    }

    @Override
    public TemporaryClassResponse getResults(Integer user_id) {
        return new TemporaryClassResponse(user_id.toString());
    }
}
