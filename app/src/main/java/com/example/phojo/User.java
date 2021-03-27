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
   /* public static String firstname;
    public static String middleinitial;
    public static String lastname;
    public static String email;
    public static String password;
    public static String userTag;*/
    public static boolean passwordPasses;

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
    /*public User (String firstname, String middleinitial, String lastname, String email,
                 String password, String userTag)
    {
        this.firstname = firstname;
        this.middleinitial = middleinitial;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.userTag = userTag;
    }*/

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


}

