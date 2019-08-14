package com.onseo.courses.ds.service;

import com.onseo.courses.ds.quiz.Quiz;
import com.onseo.courses.ds.quiz.QuizQuestion;
import com.onseo.courses.ds.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizRepository repository;
    @Autowired
    QuizQuestionService questionService;

    @Override
    public void addQuiz(Quiz quiz) {
        List<String> questionsIds = questionService.saveArray(quiz.getQuestions());
        quiz.setQuestionsIds(questionsIds);
        repository.save(quiz);
    }

    @Override
    public List<Quiz> getAllQuiz() {
       List<Quiz> quizList = (List<Quiz>) repository.findAll();
       for (Quiz quiz:quizList){
           List<QuizQuestion> questions = questionService.findByIdIn(quiz.getQuestionsIds());
           quiz.setQuestions(questions);
       }
       return quizList;
    }

    @Override
    public Quiz getQuizById(String id) {
        Quiz quiz = repository.findByQuizID(id);
        List<QuizQuestion> questions = questionService.findByIdIn(quiz.getQuestionsIds());
        quiz.setQuestions(questions);
        return quiz;
    }
}
