package com.onseo.courses.ds.quiz;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class QuizAnswerOption {

    private String answerId;
    private String text;

    @JsonCreator
    public QuizAnswerOption(@JsonProperty("answerId") String answerId, @JsonProperty("text")String text) {
        this.answerId = answerId;
        this.text = text;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
