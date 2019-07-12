package com.onseo.courses.ds.usersorg;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private String id;
    private String name;
    private String email;
    private String chiefId;
    private boolean active;
    private String role;

    @JsonCreator
    public User(@JsonProperty("id") String id, @JsonProperty("name")String name,
                @JsonProperty("email") String email, @JsonProperty("chiefId") String chiefId,
                @JsonProperty("active") boolean active, @JsonProperty("role") String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.chiefId = chiefId;
        this.active = active;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChiefId() {
        return chiefId;
    }

    public void setChiefId(String chiefId) {
        this.chiefId = chiefId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", chiefId='" + chiefId + '\'' +
                ", active=" + active +
                ", role='" + role + '\'' +
                '}';
    }
}
