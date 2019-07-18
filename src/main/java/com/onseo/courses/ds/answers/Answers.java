package com.onseo.courses.ds.answers;

import java.util.List;

public class Answers {

    private String testedUserId;
    private String questionId;
    private List<String> answersIds;

    public Answers(){

    }

    public void setTestedUserId(String id){
        this.testedUserId =  id;
    }

    public void setQuestionId(String id){
        this.questionId = id;
    }

    public void setAnswersIds(List<String> answersIds){
        this.answersIds = answersIds;
    }

    public String getTestedUserId(){
        return testedUserId;
    }

    public String getQuestionId(){
        return questionId;
    }

    public List<String> getAnswersIds(){
        return answersIds;
    }

}
