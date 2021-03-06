package com.example.phojo;

public class User {
    String firstname, middleinitial, lastname, username, passowrd, userTag;

    public User (String firstname, String middleinitial, String lastname, String username, String passowrd, String userTag) {
        this.firstname = firstname;
        this.middleinitial = middleinitial;
        this.lastname = lastname;
        this.username = username;
        this.passowrd = passowrd;
        this.userTag = userTag;
     }

    public User () {
        this.firstname = "";
        this.middleinitial = "";
        this.lastname = "";
        this.username = "";
        this.passowrd = "";
        this.userTag = "";
    }
}

