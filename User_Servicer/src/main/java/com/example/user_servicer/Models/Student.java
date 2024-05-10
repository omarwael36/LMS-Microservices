package com.example.user_servicer.Models;

import javax.json.bind.annotation.JsonbProperty;

public class Student {
    private int studentID;
    private String name;
    private String affiliation;
    private String bio;
    @JsonbProperty("UserID")
    private int userID;


    public Student() {
    }


    public Student(int userID, String name, String affiliation, String bio) {
        this.userID = userID;
        this.name = name;
        this.affiliation = affiliation;
        this.bio = bio;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
