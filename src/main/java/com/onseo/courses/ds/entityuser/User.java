package com.onseo.courses.ds.entityuser;

import java.io.IOException;
import java.util.*;

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
    public void setLastdName(String secondName) {
        this.lastName = secondName;
    }
    public void setAvatarUrl(String photo) throws IOException {this.avatar_url = ""+photo;    }
    public void setSubordinatesIds(List<String> subordinatesIds) {
        this.subordinates_id = subordinatesIds;
    }
    public void setManagerIds(List<String> managerIds) {
        this.manager_id = managerIds;
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

