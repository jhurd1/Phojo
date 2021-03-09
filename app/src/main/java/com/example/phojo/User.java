package com.example.phojo;

public class User {
    String firstname;
    String middleinitial;
    String lastname;
    String username;
    String password;
    String userTag;

    public User (String firstname, String middleinitial, String lastname, String username, String password, String userTag) {
        this.firstname = firstname;
        this.middleinitial = middleinitial;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.userTag = userTag;
    }

    public User () {
        this.firstname = "";
        this.middleinitial = "";
        this.lastname = "";
        this.username = "";
        this.password = "";
        this.userTag = "";
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddleinitial() {
        return middleinitial;
    }

    public void setMiddleinitial(String middleinitial) {
        this.middleinitial = middleinitial;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserTag() {
        return userTag;
    }

    public void setUserTag(String userTag) {
        this.userTag = userTag;
    }


}

