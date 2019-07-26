package com.onseo.courses.ds.quiz.quizSummary;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class QuizSummary {

    private String quizId;
    private String quizTitle;
    private String quizAssignmentId;
    //private QuizTargetPerson quizTargetPerson;
    private int quizPersonAnswered;
    private int quizPersonTotal;
    private QuizStatus quizStatus;

    @JsonCreator
    public QuizSummary(@JsonProperty("quizId")String quizId,
                       @JsonProperty("quizTitle") String quizTitle,
                       @JsonProperty("quizAssignmentId") String quizAssignmentId,
                       @JsonProperty("quizPersonAnswered") String quizPersonAnswered,
                       @JsonProperty("quizPersonTotal") String quizPersonTotal,
                       @JsonProperty("quizStatus") QuizStatus quizStatus)
    {
        this.quizId = quizId;
        this.quizTitle = quizTitle;
        this.quizAssignmentId = quizAssignmentId;
        this.quizPersonAnswered = Integer.valueOf(quizPersonAnswered);
        this.quizPersonTotal = Integer.valueOf(quizPersonTotal);
        this.quizStatus = quizStatus;
    }

    public void setQuizAssignmentId(String quizAssignmentId) {
        this.quizAssignmentId = quizAssignmentId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public void setQuizPersonAnswered(int quizPersonAnswered) {
        this.quizPersonAnswered = quizPersonAnswered;
    }

    public void setQuizPersonTotal(int quizPersonTotal) {
        this.quizPersonTotal = quizPersonTotal;
    }

    public void setQuizStatus(QuizStatus quizStatus) {
        this.quizStatus = quizStatus;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public int getQuizPersonAnswered() {
        return quizPersonAnswered;
    }

    public int getQuizPersonTotal() {
        return quizPersonTotal;
    }

    public QuizStatus getQuizStatus() {
        return quizStatus;
    }

    public String getQuizAssignmentId() {
        return quizAssignmentId;
    }

    public String getQuizId() {
        return quizId;
    }

    public String getQuizTitle() {
        return quizTitle;
    }
}
