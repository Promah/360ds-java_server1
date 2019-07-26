package com.onseo.courses.ds.quiz;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.onseo.courses.ds.controllers.QuizAnswerOptionControllerImpl;

import java.util.List;

public class QuizQuestion {
    private String questionId;
    private String text;
    private QuizQuestionKind questionKind;
    private int simulateAnswerCnt;
    private List<QuizAnswerOption> answerOptions;

    @JsonCreator
    public QuizQuestion(@JsonProperty("questionId") String questionId, @JsonProperty("text") String text,
                        @JsonProperty("questionKind") QuizQuestionKind quizQuestionKind){
        this.questionId = questionId;
        this.text = text;
        this.questionKind = quizQuestionKind;
        answerOptions = new QuizAnswerOptionControllerImpl().getQuizAnswers();
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public QuizQuestionKind getQuestionKind() {
        return questionKind;
    }

    public void setQuestionKind(QuizQuestionKind questionKind) {
        this.questionKind = questionKind;
    }

    public int getSimulateAnswerCnt() {
        return simulateAnswerCnt;
    }

    public void setSimulateAnswerCnt(int simulateAnswerCnt) {
        this.simulateAnswerCnt = simulateAnswerCnt;
    }

    public List<QuizAnswerOption> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(List<QuizAnswerOption> answerOptions) {
        this.answerOptions = answerOptions;
    }

    @Override
    public String toString() {
        return "QuizQuestion{" +
                "questionId='" + questionId + '\'' +
                ", text='" + text + '\'' +
                ", questionKind='" + questionKind + '\'' +
                ", answerOptions=" + answerOptions +
                '}';
    }
}
