package com.onseo.courses.ds.service;

import com.onseo.courses.ds.quiz.QuizQuestion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuizQuestionServiceImplTest {
    @Autowired
    QuizQuestionService quizQuestionService;

    @Test
    public void getAllQuizQuestion() {
        List<QuizQuestion> quizQuestionList = quizQuestionService.getAllQuizQuestion();
        Assert.assertNotNull(quizQuestionList);
    }
}
