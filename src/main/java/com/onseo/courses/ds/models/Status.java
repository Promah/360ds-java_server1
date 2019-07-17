package com.onseo.courses.ds.models;

public class Status {

    private String userId;
    private Integer activeQuizCount;
    private Integer completeQuizCount;

    public Status(String userId, Integer activeQuizCount, Integer completeQuizCount) {
        this.userId = userId;
        this.activeQuizCount = activeQuizCount;
        this.completeQuizCount = completeQuizCount;
    }

    public Status(){}

    public Integer getActiveQuizCount() {
        return activeQuizCount;
    }

    public void setActiveQuizCount(Integer activeQuizCount) {
        this.activeQuizCount = activeQuizCount;
    }

    public Integer getCompleteQuizCount() {
        return completeQuizCount;
    }

    public void setCompleteQuizCount(Integer completeQuizCount) {
        this.completeQuizCount = completeQuizCount;
    }
}
