package com.example.phojo;

import android.util.Log;
import org.testng.annotations.Test;

public class UserValidatorTest
{
    public static final String TAG = "userValidator";

    @Test
    public static void userValidatorReturnsTrue()
    {
        User user = new User();
        //assertThat(UserValidatorTest.this).hasData();
        if(user.fName == null ||
                user.lName == null ||
                user.mName == null ||
                user.pWord == null ||
                user.userName == null ||
                user.uTag == null)
        {
            Log.i(TAG, "userValidator in UserValidatorTest object failed.");
        }
    }
}
