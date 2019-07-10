package com.onseo.courses.ds.models;

import java.util.Timer;

public class Questions {

    private long id;
    private String tittle;
    private String difficulty;
    private String timer;
    private long rightAnswer_Id;
    private String answers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public long getRightAnswer_Id() {
        return rightAnswer_Id;
    }

    public void setRightAnswer_Id(long rightAnswer_Id) {
        this.rightAnswer_Id = rightAnswer_Id;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "id=" + id +
                ", tittle='" + tittle + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", timer=" + timer +
                ", rightAnswer_Id=" + rightAnswer_Id +
                ", answers='" + answers + '\'' +
                '}';
    }

}
