package com.onseo.courses.ds.controllers;

import org.springframework.web.bind.annotation.*;

//adds start path for requests
@RestController()
@RequestMapping("/api")
public class QuestionController {

    @GetMapping("/questions")
    public String getQuestions(){

        return "Some questions";
    }

    @PostMapping("/answers")
    public String getAnswers(@RequestParam(name = "usesId") Integer user_id,
                             @RequestParam(name = "questionId") Integer question_id,
                             @RequestParam(name = "answerIds") Integer[] answers_id
                             ){
        return "user_id " + user_id + ", question_id " + question_id;
    }

    @GetMapping("/summary")
    public String getSummary(){

        return "Summary of test";
    }

    @GetMapping("/results")
    public TestClassResponse getResults(@RequestParam(name = "userId") Integer user_id){

        return new TestClassResponse("Results of test for " + user_id);
    }
}
