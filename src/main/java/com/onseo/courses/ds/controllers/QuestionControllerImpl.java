package com.onseo.courses.ds.controllers;

import com.onseo.courses.ds.interfaces.BaseQuestionController;
import com.onseo.courses.ds.models.Questions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuestionControllerImpl implements BaseQuestionController {

    @Override
    public List<Questions> getQuestions() {
        int size = 5;
        List<Questions> questions = new ArrayList<>();
        for (int i = 0; i < size; i++){
            Questions question = new Questions();
            question.setId(i + "");
            question.setTittle("title №" + i);

            questions.add(question);
        }
        return questions;
    }

    @Override
    public TemporaryClassResponse getAnswers(String userId, String questionId, String answersId) {
        return new TemporaryClassResponse(userId + " " + questionId + " " + answersId);
    }

    @Override
    public List<TemporaryClassResponse>  getSummary() {
        int size = 3;
        List<TemporaryClassResponse> summary = new ArrayList<>();
        for (int i = 0; i < size; i++){
            summary.add(new TemporaryClassResponse("summary " + i));
        }
        return summary;
    }

    @Override
    public List<TemporaryClassResponse>  getResults(String userId) {
        int size = 3;
        List<TemporaryClassResponse> results = new ArrayList<>();
        for (int i = 0; i < size; i++){
            results.add(new TemporaryClassResponse(userId + " " + i));
        }
        return results;
    }
}
