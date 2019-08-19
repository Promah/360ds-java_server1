package com.onseo.courses.ds.service;

import com.onseo.courses.ds.quiz.QuizQuestion;

import java.util.List;

public interface QuizQuestionService {
    List<QuizQuestion> getAllQuizQuestion();
    List<QuizQuestion> findByIdIn(List<String> ids);
    List<String> saveArray(List<QuizQuestion> questions);
    void save(QuizQuestion question);
}
