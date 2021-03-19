package com.example.phojo;
import android.util.Log;

import org.testng.annotations.Test;

//import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {

    private static final String TAG = "registerTag";

    @Test
    void onClick()
    {
        User user = new User();
        //assertThat(UserValidatorTest.this).hasData();
        if(user.getEmail() == null ||
                user.getFirstname() == null ||
                user.getMiddleinitial() == null ||
                user.getLastname() == null ||
                user.getPassword() == null ||
                user.getUserTag() == null)
        {
            Log.i(TAG, "userValidator in UserValidatorTest object failed.");
        }
    }
}