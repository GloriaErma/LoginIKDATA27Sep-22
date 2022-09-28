package com.ikno.login.Models;

public class User {
    public String username;
    public String password;
    public String getusername() {
        return username;
    }
    public void setusername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // Constructor, getters and setters
    
}