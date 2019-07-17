package com.onseo.courses.ds.answers;

public class Answers {

    private String testedUserId;
    private String questionId;
    private String[] answersIds;

    public Answers(){

    }

    public void setTestedUserId(String id){
        this.testedUserId =  id;
    }

    public void setQuestionId(String id){
        this.questionId = id;
    }

    public void setAnswersIds(String[] answersIds){
        this.answersIds = answersIds;
    }

    public String getTestedUserId(){
        return testedUserId;
    }

    public String getQuestionId(){
        return questionId;
    }

    public String[] getAnswersIds(){
        return answersIds;
    }

}
