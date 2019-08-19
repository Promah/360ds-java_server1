package com.onseo.courses.ds.entityuser;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
@Document(collection = "users")
public class User {

    @Id
    private String id;
    @Field("firstName")
    private String firstName;
    @Field("lastName")
    private String lastName;
    @Field("avatar_url")
    private String avatarUrl;
    @Field("subordinates_id")
    private List<String> subordinatesId;
    @Field("manager_id")
    private List<String> managerId;
    @Field("uId")
    private String uId;

    public User() {
    }

    public User(String firstName, String lastName, String uId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.uId = uId;
    }

    public User(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.avatarUrl = user.getAvatarUrl();
        this.subordinatesId = user.getSubordinatesIds();
        this.managerId = user.getManagerIds();
        this.uId = user.getuId();
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String secondName) {
        this.lastName = secondName;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    //    public void setAvatarUrl(String photo) {this.avatar_url = ""+photo;    }

    public void setSubordinatesId(List<String> subordinatesId) {
        this.subordinatesId = subordinatesId;
    }

    public void setManagerId(List<String> managerId) {
        this.managerId = managerId;
    }

    public String getId() {
        return id;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getAvatarUrl() {return avatarUrl;}
    public List<String> getSubordinatesIds() { return subordinatesId; }
    public List<String> getManagerIds() {
        return managerId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + lastName + '\'' +
                ", avatar_url=" + avatarUrl +
                '}';
    }
}

