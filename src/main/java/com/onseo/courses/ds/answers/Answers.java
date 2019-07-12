package com.onseo.courses.ds.answers;

public class Answers {

    private String tested_user_id;
    private String question_id;
    private String[] answers_ids;

    public Answers(){

    }

    public void setTested_user_id(String id){
        this.tested_user_id =  id;
    }

    public void setQuestion_id(String id){
        this.question_id = id;
    }

    public void setAnswers_ids(String[] answers_ids){
        this.answers_ids = answers_ids;
    }

    public String getTested_user_id(){
        return tested_user_id;
    }

    public String getQuestion_id(){
        return question_id;
    }

    public String[] getAnswers_ids(){
        return answers_ids;
    }

}
