package com.onseo.courses.ds.quiz;

import java.util.List;

public class QuizQuestion {
    private String questionId;
    private String text;
    private String questionKind;
    private static final int SIMULATE_ANSWER_CNT = 4;
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

    public String getQuestionKind() {
        return questionKind;
    }

    public void setQuestionKind(String questionKind) {
        this.questionKind = questionKind;
    }

    public static int getSimulateAnswerCnt() {
        return SIMULATE_ANSWER_CNT;
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
