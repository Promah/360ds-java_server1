package com.onseo.courses.ds.quiz;

import java.util.List;

public class QuizAnswerData {
    private String questionId;
    private List<String> answerItems;
    private String answerData;

    public QuizAnswerData() {
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public List<String> getAnswerItems() {
        return answerItems;
    }

    public void setAnswerItems(List<String> answerItems) {
        this.answerItems = answerItems;
    }

    public String getAnswerData() {
        return answerData;
    }

    public void setAnswerData(String answerData) {
        this.answerData = answerData;
    }
}
