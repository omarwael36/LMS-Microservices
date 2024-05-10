package com.example.user_servicer.Models;

public class User {
    private int UserID;
    private String Email;
    private String Password;
    private String Role;

    public User() {
    }

    public User(String email, String password, String role) {
        this.Email = email;
        this.Password = password;
        this.Role = role;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}
