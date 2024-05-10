package com.example.user_servicer.Models;

import javax.json.bind.annotation.JsonbProperty;

public class Instructor {
    private int instructorID;
    private String name;
    private String affiliation;
    private String bio;
    private int yearsOfExperience;
    @JsonbProperty("UserID")
    private int userID;

    public Instructor() {
    }


    public Instructor(int userID, String name, String affiliation, String bio, int yearsOfExperience) {
        this.userID = userID;
        this.name = name;
        this.affiliation = affiliation;
        this.bio = bio;
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
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

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
