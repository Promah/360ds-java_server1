package com.onseo.courses.ds.controllers;

import com.onseo.courses.ds.interfaces.BaseQuestionController;
import com.onseo.courses.ds.models.Questions;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionControllerImpl implements BaseQuestionController {

    @Override
    public Questions[] getQuestions() {
        int size = 5;
        Questions[] questions = new Questions[size];
        for (int i = 0; i < size; i++){
            Questions question = new Questions();
            question.setId(i + "");
            question.setTittle("title №" + i);

            questions[i] = question;
        }
        return questions;
    }

    @Override
    public TemporaryClassResponse getAnswers(String userId, String questionId, String answersId) {
        return new TemporaryClassResponse(userId + " " + questionId + " " + answersId);
    }

    @Override
    public TemporaryClassResponse[] getSummary() {
        int size = 3;
        TemporaryClassResponse[] results = new TemporaryClassResponse[size];
        for (int i = 0; i < size; i++){
            results[i] = new TemporaryClassResponse("summary " + i);
        }
        return results;
    }

    @Override
    public TemporaryClassResponse[] getResults(String userId) {
        int size = 3;
        TemporaryClassResponse[] results = new TemporaryClassResponse[size];
        for (int i = 0; i < size; i++){
            results[i] = new TemporaryClassResponse(userId + " " + i);
        }
        return results;
    }
}
