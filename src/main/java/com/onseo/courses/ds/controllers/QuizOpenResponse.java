package com.onseo.courses.ds.controllers;

import com.onseo.courses.ds.quiz.Quiz;
import com.onseo.courses.ds.quiz.QuizResponse;
import com.onseo.courses.ds.quiz.quizSummary.QuizSummary;

import java.util.List;

public class QuizOpenResponse {
    private List<QuizSummary> summary;
    private List<Quiz> quiz;
    private List<QuizResponse> answers;

    public QuizOpenResponse(List<QuizSummary> summary, List<Quiz> quiz, List<QuizResponse> answers) {
        this.summary = summary;
        this.quiz = quiz;
        this.answers = answers;
    }

    public List<QuizSummary> getQuizSummary() {
        return summary;
    }
    public List<Quiz> getQuiz() {
        return quiz;
    }
    public List<QuizResponse> getQuizResponse() {
        return answers;
    }
}
