package com.onseo.courses.ds.service;

import com.onseo.courses.ds.quiz.QuizAnswerOption;

import java.util.List;

public interface QuizAnswerOptionService{
    QuizAnswerOption findById(String id);
    void save(QuizAnswerOption quizAnswerOption);
    List<String> saveArray(List<QuizAnswerOption> quizAnswerOptions);
    List<QuizAnswerOption> findByIdIn(List<String> ids);
}
