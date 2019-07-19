package com.onseo.courses.ds.interfaces;

import com.onseo.courses.ds.controllers.QuizOpenResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public interface QuizController {

    @GetMapping("/quiz/open/{quizAssignmentID}")
    QuizOpenResponse getOpenQuiz(@PathVariable String quizAssignmentID);
}

