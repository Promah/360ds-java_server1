package com.onseo.courses.ds.interfaces;

import com.onseo.courses.ds.controllers.TemporaryClassResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api")
public interface BaseQuestionController {

    @GetMapping("/questions")
    public String getQuestions();

    @PostMapping("/answers")
    public String getAnswers(@RequestParam(name = "usesId") Integer user_id,
                             @RequestParam(name = "questionId") Integer question_id,
                             @RequestParam(name = "answerIds") Integer[] answers_id
    );

    @GetMapping("/summary")
    public String getSummary();

    @GetMapping("/results")
    public TemporaryClassResponse getResults(@RequestParam(name = "userId") Integer user_id);
}
