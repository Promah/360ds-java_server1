package com.onseo.courses.ds.quiz;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class QuizAnswerData {
    private String questionId;
    private List<String> answerItems;
    private String answerData;

    @JsonCreator
    public QuizAnswerData(@JsonProperty("questionId")String questionId,
                          @JsonProperty("answerItems") List<String> answerItems,
                          @JsonProperty("answerData")String answerData)
    {
        this.questionId = questionId;
        this.answerItems = answerItems;
        this.answerData = answerData;
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
