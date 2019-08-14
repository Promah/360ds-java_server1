package com.onseo.courses.ds.quiz;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.onseo.courses.ds.controllers.QuizQuestionController;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "quiz")
public class Quiz {
    @Id
    private String quizID;
    @Field("quizTitle")
    private String quizTitle;
    @Field("quizDescription")
    private String quizDescription;
    @Field("quizAssignmentID")
    private String quizAssignmentID;
    @Field("questions")
    @DBRef
    private List<String> questionsIds;

    private List<QuizQuestion> questions;

    @JsonCreator
    public Quiz(@JsonProperty("quizID") String quizID, @JsonProperty("quizTitle")String quizTitle,
                @JsonProperty("quizDescription") String quizDescription, @JsonProperty("quizAssignmentID") String quizAssignmentID) {
        this.quizID = quizID;
        this.quizTitle = quizTitle;
        this.quizDescription = quizDescription;
        this.quizAssignmentID = quizAssignmentID;
        this.questions = new QuizQuestionController().getQuizQuestions();
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

    public List<String> getQuestionsIds() {
        return questionsIds;
    }

    public void setQuestionsIds(List<String> questionsIds) {
        this.questionsIds = questionsIds;
    }
}
