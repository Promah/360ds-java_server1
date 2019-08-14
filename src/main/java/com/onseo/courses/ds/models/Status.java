package com.onseo.courses.ds.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "user_status")
public class Status {

    @Id
    private String id;
    @Field("user_id")
    private String userId;
    @Field("active_quiz_cnt")
    private Integer activeQuizCnt;
    @Field("complete_quiz_cnt")
    private Integer completeQuizCnt;

    public Status(String userId, Integer activeQuizCount, Integer completeQuizCount) {
        this.userId = userId;
        this.activeQuizCnt = activeQuizCount;
        this.completeQuizCnt = completeQuizCount;
    }

    public Status(){}

    public Integer getActiveQuizCnt() {
        return activeQuizCnt;
    }

    public void setActiveQuizCnt(Integer activeQuizCount) {
        this.activeQuizCnt = activeQuizCount;
    }

    public Integer getCompleteQuizCnt() {
        return completeQuizCnt;
    }

    public void setCompleteQuizCnt(Integer completeQuizCnt) {
        this.completeQuizCnt = completeQuizCnt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
