package com.onseo.courses.ds.quiz;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.onseo.courses.ds.controllers.QuizAnswerDataController;

import java.util.List;

public class QuizResponse {
    private String responserProfile;
    private List<QuizAnswerData> answers;

    @JsonCreator
    public QuizResponse(@JsonProperty("responserProfile") String responserProfile) {
        this.responserProfile = responserProfile;
        answers = new QuizAnswerDataController().getQuizAnswerData();
    }

    public String getResponserProfile() {
        return responserProfile;
    }

    public void setResponserProfile(String responserProfile) {
        this.responserProfile = responserProfile;
    }

    public List<QuizAnswerData> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuizAnswerData> answers) {
        this.answers = answers;
    }
}
