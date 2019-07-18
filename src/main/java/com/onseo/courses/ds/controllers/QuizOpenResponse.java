package com.onseo.courses.ds.controllers;

import com.onseo.courses.ds.quiz.Quiz;

public class QuizOpenResponse {
    private Quiz quiz;

    public QuizOpenResponse(Quiz quiz) {
        this.quiz = quiz;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
