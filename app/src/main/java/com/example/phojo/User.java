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
    public static String firstname;
    public static String middleinitial;
    public static String lastname;
    public static String email;
    public static String password;
    public static String userTag;
    public static boolean passwordPasses;

    /**********************************
     * MUTATORS
     ********************************
     * @return*/
    public void setPassword(String password)
    {
        Login l = new Login();
        this.password = password;
        password = l.getEtPassword().toString();
    }

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
        return this.password;
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
        this.firstname = firstname; // try the getter next
        this.middleinitial = middleinitial;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.userTag = userTag;
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
        setPassword(password);
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

