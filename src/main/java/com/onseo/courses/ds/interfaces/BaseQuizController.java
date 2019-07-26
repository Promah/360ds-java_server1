package com.onseo.courses.ds.interfaces;

import com.onseo.courses.ds.controllers.QuizOpenResponse;
import com.onseo.courses.ds.controllers.ResponseContainer;
import com.onseo.courses.ds.quiz.quizSummary.QuizSummary;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/quiz")
public interface BaseQuizController {

    @GetMapping("/list")
    ResponseContainer<List<QuizSummary>> getQuizList() throws IOException;

    @GetMapping("/open/{quizAssignmentID}")
    QuizOpenResponse getOpenQuiz(@PathVariable String quizAssignmentID);
}
