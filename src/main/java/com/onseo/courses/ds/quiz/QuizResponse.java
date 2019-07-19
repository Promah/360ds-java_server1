package com.onseo.courses.ds.quiz;

import java.util.List;

public class QuizResponse {
    private String responserProfile;
    private List<QuizAnswerData> answers;

    public QuizResponse() {
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
