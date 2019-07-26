package com.onseo.courses.ds.quiz;

import java.util.List;

public class QuizQuestion {
    private String questionId;
    private String text;
    private QuizQuestionKind questionKind;
    private int simulateAnswerCnt;
    private List<QuizAnswerOption> answerOptions;

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
