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
    public String getAnswers(@RequestParam(name = "userId") String user_id,
                             @RequestParam(name = "questionId") String question_id,
                             @RequestParam(name = "answerIds") String answers_id
    );

    @GetMapping("/summary")
    public String getSummary();

    @GetMapping("/results")
    public TemporaryClassResponse getResults(@RequestParam(name = "userId") String user_id);
}
