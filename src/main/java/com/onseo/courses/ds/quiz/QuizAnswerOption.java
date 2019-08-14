package com.onseo.courses.ds.quiz;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "quiz_question_answer_option")
public class QuizAnswerOption {

    @Id
    private String answerId;
    @Field("text")
    private String text;

    @JsonCreator
    public QuizAnswerOption(@JsonProperty("answerId") String answerId, @JsonProperty("text")String text) {
        this.answerId = answerId;
        this.text = text;
    }

    public QuizAnswerOption(String text){
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
