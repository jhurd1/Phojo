package com.example.phojo;

public class User {
    String firstname, middleinitial, lastname, username, passowrd;

    public User (String firstname, String middleinitial, String lastname, String username, String passowrd) {
        this.firstname = firstname;
        this.middleinitial = middleinitial;
        this.lastname = lastname;
        this.username = username;
        this.passowrd = passowrd;
    }

    public User () {
        this.firstname = "";
        this.middleinitial = "";
        this.lastname = "";
        this.username = "";
        this.passowrd = "";
    }
}

