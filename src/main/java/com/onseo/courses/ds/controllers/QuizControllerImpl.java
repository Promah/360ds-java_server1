package com.onseo.courses.ds.controllers;

import com.onseo.courses.ds.interfaces.QuizController;
import com.onseo.courses.ds.quiz.Quiz;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizControllerImpl implements QuizController {
    @Override
    public QuizOpenResponse getOpenQuiz(String quizAssignmentID) {
        Quiz quiz = new Quiz();
        return new QuizOpenResponse(quiz);
    }
}
