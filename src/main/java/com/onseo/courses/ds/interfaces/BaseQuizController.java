package com.onseo.courses.ds.interfaces;

import com.onseo.courses.ds.controllers.QuizOpenResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quiz")
public interface BaseQuizController {

    @GetMapping("/list")
    public void getQuizList();

    @GetMapping("/open/{quizAssignmentID}")
    QuizOpenResponse getOpenQuiz(@PathVariable String quizAssignmentID);
}
