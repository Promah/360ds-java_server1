package com.onseo.courses.ds.service;

import com.onseo.courses.ds.quiz.Quiz;

import java.util.List;

public interface QuizService {
    void addQuiz(Quiz quiz);
    List<Quiz> getAllQuiz();
    Quiz getQuizById(String id);
}
