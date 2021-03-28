package com.example.phojo;

import android.widget.EditText;

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
    private boolean passwordPasses;

    /*********************************
     * CONSTRUCTORS
     ********************************/

    /********************************
     * Login Constructor for User
     * @param etUsername
     * @param etPassword
     *******************************/
    public User(EditText etUsername, EditText etPassword)
    {

    }

    /*********************************
     * User Constructor
     * @param firstname
     * @param middleinitial
     * @param lastname
     * @param email
     * @param password
     * @param userTag
     ********************************/
    public User (String firstname, String middleinitial, String lastname, String email,
                 String password, String userTag)
    {
        this.firstname = firstname;
        this.middleinitial = middleinitial;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.userTag = userTag;
    }

    /*********************************
     * User Default Constructor
     *******************************/
   public User()
    {
        /*this.firstname = firstname; // try the getter next
        this.middleinitial = middleinitial;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.userTag = userTag;*/
    }

    /*********************************
     * ACCESSORS AND MUTATORS
     *******************************/
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

