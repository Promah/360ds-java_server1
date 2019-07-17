package com.onseo.courses.ds.interfaces;

import com.onseo.courses.ds.controllers.TemporaryClassResponse;
import com.onseo.courses.ds.models.Questions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api")
public interface BaseQuestionController {

    @PostMapping("/questions")
    public Questions[] getQuestions();

    @GetMapping("/answers")
    public TemporaryClassResponse getAnswers(@RequestParam(name = "userId") String userId,
                             @RequestParam(name = "questionId") String questionId,
                             @RequestParam(name = "answerIds") String answersId
    );

    @GetMapping("/summary")
    public TemporaryClassResponse[] getSummary();

    @GetMapping("/results")
    public TemporaryClassResponse[] getResults(@RequestParam(name = "userId") String userId);
}
