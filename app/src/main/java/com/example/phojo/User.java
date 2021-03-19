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
    private String firstname;
    private String middleinitial;
    private String lastname;
    private String email;
    private String password;
    private String userTag;

    /**********************************
     * ACCESSORS
     *********************************/
    public String getFirstname() {
        return firstname;
    }

    public String getMiddleinitial() {
        return middleinitial;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public String getUserTag() {
        return userTag;
    }
    /*********************************
     * CONSTRUCTORS
     ********************************/

    /*********************************
     * User Constructor
     * @param firstname
     * @param middleinitial
     * @param lastname
     * @param email
     * @param password
     * @param userTag
     ********************************/
    public User (String firstname, String middleinitial, String lastname, String email, String password, String userTag) {
        this.firstname = firstname;
        this.middleinitial = middleinitial;
        this.lastname = lastname;
        this.email = email;
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
        this.email = "";
        this.password = "";
        this.userTag = "";
    }
}

