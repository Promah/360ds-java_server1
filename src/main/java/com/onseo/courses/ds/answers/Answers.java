package com.onseo.courses.ds.answers;

public class Answers {

    private String TESTED_USER_ID;
    private String QUESTION_ID;
    private String[] ANSWERS_IDS;

    public Answers(){

    }

    public void setTESTED_USER_ID(String id){
        this.TESTED_USER_ID =  id;
    }

    public void setQUESTION_ID(String id){
        this.QUESTION_ID = id;
    }

    public void setANSWERS_IDS(String[] answers_ids){
        this.ANSWERS_IDS = answers_ids;
    }

    public String getTESTED_USER_ID(){
        return TESTED_USER_ID;
    }

    public String getQUESTION_ID(){
        return QUESTION_ID;
    }

    public String[] getANSWERS_IDS(){
        return ANSWERS_IDS;
    }

}
