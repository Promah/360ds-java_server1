package com.onseo.courses.ds.service;

import com.onseo.courses.ds.quiz.QuizAnswerOption;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuizAnswerOptionServiceImplTest {

    @Autowired
    QuizAnswerOptionService quizAnswerOptionService;

    @Test
    public void findById() {
    }

    @Test
    public void save() {
        QuizAnswerOption answerOption = new QuizAnswerOption("temp text");
        quizAnswerOptionService.save(answerOption);
    }

    @Test
    public void saveArray() {
    }
}
