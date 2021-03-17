package com.example.phojo;

/**
 * USER CLASS
 * A class for containing and passing
 * user information.
 *
 * @author edgarcobian
 * @author chrisfowler
 * @author danallewellyn
 * @author jamiehurd
 */
public class User {

    /***********************************
     * DATA MEMBERS
     **********************************/
    String firstname;
    String middleinitial;
    String lastname;
    String username;
    String password;
    String userTag;

    /*********************************
     * CONSTRUCTORS
     ********************************/

    /*********************************
     * User Constructor
     * @param firstname
     * @param middleinitial
     * @param lastname
     * @param username
     * @param password
     * @param userTag
     ********************************/
    public User (String firstname, String middleinitial, String lastname, String username, String password, String userTag) {
        this.firstname = firstname;
        this.middleinitial = middleinitial;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.userTag = userTag;
    }

    /*********************************
     * User Non-default Constructor
     ********************************/
    public User () {
        this.firstname = "";
        this.middleinitial = "";
        this.lastname = "";
        this.username = "";
        this.password = "";
        this.userTag = "";
    }
}

