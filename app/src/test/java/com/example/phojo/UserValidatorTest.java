package com.example.phojo;

import android.util.Log;
import org.testng.annotations.Test;

/**
 * USERVALIDATORTEST
 * A class for testing
 * the validity of the user
 * object.
 *
 * @author edgarcobian
 * @author chrisfowler
 * @author danallewellyn
 * @author jamiehurd
 */
public class UserValidatorTest
{
    /**********************************
     * DATA MEMBERS
     ********************************/
    public static final String TAG = "userValidator";

    /**********************************
     * userValidatorReturnsTrue
     * Return true if the
     * user object's variables
     * contain data.
     **********************************/
    @Test
    public static void userValidatorReturnsTrue()
    {
        User user = new User(etFirstName, etMiddleName, etLastName, etUsername, etPassword);
        //assertThat(UserValidatorTest.this).hasData();
        if(user.getEmail() == null ||
                user.getFirstname() == null ||
                user.getMiddleinitial() == null ||
                user.getLastname() == null ||
                user.getPassword() == null ||
                user.getUserTag() == null)
        {
            Log.i(TAG, "userValidator in UserValidatorTest object failed.");
        } else
        {
            Log.i(TAG, "userValidator verified some user data received.");
        }
    }
}