package com.onseo.courses.ds.interfaces;

import com.onseo.courses.ds.controllers.QuizOpenResponse;
import com.onseo.courses.ds.controllers.ResponseContainer;
import com.onseo.courses.ds.quiz.quizSummary.QuizSummary;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/quiz")
public interface BaseQuizController {

    @GetMapping("/list")
    ResponseContainer<List<QuizSummary>> getQuizList() throws IOException;

    @GetMapping("/open/{quizAssignmentID}")
    QuizOpenResponse getOpenQuiz(@PathVariable String quizAssignmentID);

    @PostMapping("/submit/{quizAssignmentID}")
    Object postQuizSubmit(@PathVariable(name = "quizAssignmentID") String quizAssignmentID) throws IOException, org.json.simple.parser.ParseException;
}
