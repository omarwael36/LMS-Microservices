package com.example.user_servicer.Models;

public class CenterCredintials {
    private String email;
    private String password;

    public CenterCredintials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
