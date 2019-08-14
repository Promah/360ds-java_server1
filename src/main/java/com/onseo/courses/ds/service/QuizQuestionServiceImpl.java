package com.onseo.courses.ds.service;

import com.onseo.courses.ds.quiz.QuizAnswerOption;
import com.onseo.courses.ds.quiz.QuizQuestion;
import com.onseo.courses.ds.repository.QuizQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuizQuestionServiceImpl implements QuizQuestionService {

    @Autowired
    QuizQuestionRepository repository;
    @Autowired
    QuizAnswerOptionService answerOptionService;

    @Override
    public List<QuizQuestion> getAllQuizQuestion() {
        List<QuizQuestion> quizQuestionList = (List<QuizQuestion>) repository.findAll();
        for(QuizQuestion question: quizQuestionList){
            List<QuizAnswerOption> answerOptions = answerOptionService.findByIdIn(question.getAnswerOptionsIds());
            question.setAnswerOptions(answerOptions);
        }
        return quizQuestionList;
    }

    @Override
    public List<QuizQuestion> findByIdIn(List<String> ids) {
        List<QuizQuestion> questions = repository.findAllByQuestionIdIn(ids);
        for(QuizQuestion question: questions){
            List<QuizAnswerOption> answerOptions = answerOptionService.findByIdIn(question.getAnswerOptionsIds());
            question.setAnswerOptions(answerOptions);
        }
        return questions;
    }

    @Override
    public List<String> saveArray(List<QuizQuestion> questions) {
        for(QuizQuestion question:questions){
            List<String> answerOptionsIds = answerOptionService.saveArray(question.getAnswerOptions());
            question.setAnswerOptionsIds(answerOptionsIds);
            repository.save(question);
        }
        return null;
    }

    @Override
    public void save(QuizQuestion question) {
        List<String> answerOptionsIds = answerOptionService.saveArray(question.getAnswerOptions());
        question.setAnswerOptionsIds(answerOptionsIds);
        repository.save(question);
    }
}
