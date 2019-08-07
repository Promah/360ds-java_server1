package com.onseo.courses.ds.admin.interfaces;

import com.onseo.courses.ds.controllers.QuizOpenResponse;
import com.onseo.courses.ds.quiz.Quiz;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public interface BaseAdminQuizController {

    @RequestMapping(value = "/quiz", params = "quizId")
    Quiz getQuiz(@RequestParam String quizId);

    @RequestMapping(value = "/quiz")
    Quiz postQuiz(@RequestBody Quiz quiz);
}

