package com.example.user_servicer.Models;

public class AdminResponse {
    private int UserID;
    private String name;
    private String bio;
    private String affiliation;
    private int experience;
    private String role;
    private String email;
    private String password;

    public AdminResponse() {
    }

    public AdminResponse(int userID, String name, String bio, String affiliation, int experience, String role, String email, String password) {
        UserID = userID;
        this.name = name;
        this.bio = bio;
        this.affiliation = affiliation;
        this.experience = experience;
        this.role = role;
        this.email = email;
        this.password = password;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}