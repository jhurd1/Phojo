package com.example.phojo;

public class User {
    User u = new User();
    public String fName = u.firstname;
    public String mName = u.middleinitial;
    public String lName = u.lastname;
    public String userName = u.username;
    public String pWord = u.password;
    public String uTag = u.userTag;
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
}

