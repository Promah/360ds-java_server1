package com.onseo.courses.ds.admin.interfaces;

import com.onseo.courses.ds.controllers.QuizOpenResponse;
import com.onseo.courses.ds.quiz.Quiz;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public interface BaseAdminQuizController {

    @RequestMapping(value = "/quiz", params = "quizId")
    String getQuiz(@RequestHeader(name="accessToken")String token,
                   @RequestParam String quizId);

    @RequestMapping(value = "/quiz")
    String postQuiz(@RequestHeader(name="accessToken")String token,
                    @RequestBody Quiz quiz);
}

