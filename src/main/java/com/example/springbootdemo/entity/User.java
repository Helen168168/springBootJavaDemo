package com.example.springbootdemo.entity;

public class User {

    private String id;
    private String name;

    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }


    // Getter for id
    public String getId() {
        return id;
    }

    // Setter for id
    public void setId(String id) {
        this.id = id;
    }

    // Getter for username
    public String getName() {
        return name;
    }

    // Setter for username
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {return password ;}
    public void setPassword(String password) {
        this.password = password;
    }


}
