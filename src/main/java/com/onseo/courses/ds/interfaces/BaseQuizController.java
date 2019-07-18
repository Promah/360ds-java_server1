package com.onseo.courses.ds.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quiz")
public interface BaseQuizController {

    @GetMapping("/list")
    public void getQuizList();

    @GetMapping("/open/${quizAssignmentID}")
    public void getQuiz(@PathVariable String quizAssignmentId);
}
