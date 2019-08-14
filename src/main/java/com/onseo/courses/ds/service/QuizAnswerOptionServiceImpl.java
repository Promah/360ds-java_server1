package com.onseo.courses.ds.service;

import com.onseo.courses.ds.quiz.QuizAnswerOption;
import com.onseo.courses.ds.repository.QuizAnswerOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class QuizAnswerOptionServiceImpl implements QuizAnswerOptionService {
    @Autowired
    QuizAnswerOptionRepository repository;
    @Override
    public QuizAnswerOption findById(String id) {
        return repository.findByAnswerId(id);
    }

    @Override
    public void save(QuizAnswerOption quizAnswerOption) {
        repository.save(quizAnswerOption);
    }

    @Override
    public List<String> saveArray(List<QuizAnswerOption> quizAnswerOptions) {
        List<String> answerOptionIds = new ArrayList<>();
        for(QuizAnswerOption answerOption:quizAnswerOptions){
            repository.save(answerOption);
            answerOptionIds.add(answerOption.getAnswerId());
        }
        return answerOptionIds;
    }

    @Override
    public List<QuizAnswerOption> findByIdIn(List<String> ids) {
        repository.findAllByAnswerIdIn(ids);

        return null;
    }



}
