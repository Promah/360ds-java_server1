package com.onseo.courses.ds.entityuser;

import java.util.List;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String avatar_url;
    private List<String> subordinates_id;
    private List<String> manager_id;

    public void setId(String id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String secondName) {
        this.lastName = secondName;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    //    public void setAvatarUrl(String photo) {this.avatar_url = ""+photo;    }

    public void setSubordinates_id(List<String> subordinates_id) {
        this.subordinates_id = subordinates_id;
    }

    public void setManager_id(List<String> manager_id) {
        this.manager_id = manager_id;
    }

    public String getId() {
        return id;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastdName() {
        return lastName;
    }
    public String getAvatarUrl() {return avatar_url;}
    public List<String> getSubordinatesIds() { return subordinates_id; }
    public List<String> getManagerIds() {
        return manager_id;
    }


    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + lastName + '\'' +
                ", avatar_url=" + avatar_url +
                '}';
    }
}

