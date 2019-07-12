package com.onseo.courses.ds.models;


public class Questions {

    private String id;
    private String tittle;
    private String difficulty;
    private String timer;
    private String rightAnswer_Id;
    private String answers;
    private String showInputField;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getRightAnswer_Id() {
        return rightAnswer_Id;
    }

    public void setRightAnswer_Id(String rightAnswer_Id) {
        this.rightAnswer_Id = rightAnswer_Id;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getShowInputField() {
        return showInputField;
    }

    public void setShowInputField(String showInputField) {
        this.showInputField = showInputField;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "id=" + id +
                ", tittle='" + tittle + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", timer='" + timer + '\'' +
                ", rightAnswer_Id=" + rightAnswer_Id +
                ", answers='" + answers + '\'' +
                ", showInputField='" + showInputField + '\'' +
                '}';
    }
}
