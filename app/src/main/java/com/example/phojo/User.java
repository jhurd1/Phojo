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
     *******************************/
    public User() {
        this.firstname = "";
        this.middleinitial = "";
        this.lastname = "";
        this.email = "";
        this.password = "";
        this.userTag = "";
    }

    /*********************************
     * Enforcer()
     * Enforce password requirements
     * @param passwordPasses
     * exit the loop upon finding a
     * digit and special char.
     * If no digit and char is found,
     * the flag remains false and
     * the password fails.
     ********************************/
    public boolean enforcePassword(boolean passwordPasses)
    {
        Register r = new Register();
        password = r.getEtPassword().toString();
        char[] charArray =
                {
                   '!', '@', '#', '$', '%', '^',
                   '&', '*'
                };
        for(int i = 0; i < password.length(); i++)
        {
            boolean yesDigit = Character.isDigit(password.charAt(i));

            if(yesDigit)
            {
                passwordPasses = true;
            } else
            {
                passwordPasses = false;
            }
            for(int j = 0; j < charArray.length; j++)
            {
                if (passwordPasses && password.equals(charArray[j]))
                {
                    passwordPasses = true;
                    break; // if password passes here, we're done with the test
                } else
                {
                    passwordPasses = false;
                }
            }
        }
        return passwordPasses;
    }
}

