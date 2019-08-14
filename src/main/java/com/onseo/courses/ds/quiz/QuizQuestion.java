package com.onseo.courses.ds.quiz;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.onseo.courses.ds.controllers.QuizAnswerOptionControllerImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "quiz_question")
public class QuizQuestion {
    @Id
    private String questionId;
    @Field("text")
    private String text;
    @Field("questionKind")
    private QuizQuestionKind quizQuestionKind;
    @Field("simulateAnswerCnt")
    private int simulateAnswerCnt;
    @Field("answer_options")
    @DBRef
    private List<String> answerOptionsIds;

    private List<QuizAnswerOption> answerOptions;

    @JsonCreator
    public QuizQuestion(@JsonProperty("questionId") String questionId, @JsonProperty("text") String text,
                        @JsonProperty("questionKind") QuizQuestionKind quizQuestionKind){
        this.questionId = questionId;
        this.text = text;
        this.quizQuestionKind = quizQuestionKind;
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

    public QuizQuestionKind getQuizQuestionKind() {
        return quizQuestionKind;
    }

    public void setQuizQuestionKind(QuizQuestionKind quizQuestionKind) {
        this.quizQuestionKind = quizQuestionKind;
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

    public List<String> getAnswerOptionsIds() {
        return answerOptionsIds;
    }

    public void setAnswerOptionsIds(List<String> answerOptionsIds) {
        this.answerOptionsIds = answerOptionsIds;
    }

    @Override
    public String toString() {
        return "QuizQuestion{" +
                "questionId='" + questionId + '\'' +
                ", text='" + text + '\'' +
                ", questionKind='" + quizQuestionKind + '\'' +
                ", answerOptions=" + answerOptions +
                '}';
    }
}
