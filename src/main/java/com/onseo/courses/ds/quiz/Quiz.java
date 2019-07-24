package com.onseo.courses.ds.quiz;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Quiz {
    private String quizID;
    private String quizTitle;
    private String quizDescription;
    private String quizAssignmentID;
    private List<QuizQuestion> questions;


    @JsonCreator
    public Quiz(@JsonProperty("quizID") String quizID, @JsonProperty("quizTitle")String quizTitle,
                @JsonProperty("quizDescription") String quizDescription, @JsonProperty("quizAssignmentID") String quizAssignmentID,
                @JsonProperty("questions") List<QuizQuestion> questions) {
        this.quizID = quizID;
        this.quizTitle = quizTitle;
        this.quizDescription = quizDescription;
        this.quizAssignmentID = quizAssignmentID;
        this.questions = questions;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public String getQuizDescription() {
        return quizDescription;
    }

    public void setQuizDescription(String quizDescription) {
        this.quizDescription = quizDescription;
    }

    public String getQuizAssignmentID() {
        return quizAssignmentID;
    }

    public void setQuizAssignmentID(String quizAssignmentID) {
        this.quizAssignmentID = quizAssignmentID;
    }

    public List<QuizQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuizQuestion> questions) {
        this.questions = questions;
    }
}
