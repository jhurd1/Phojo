package com.example.phojo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class UserLandingPage extends AppCompatActivity {

    /************************************
     * PRIVATE MEMBERS
     ************************************/
    UserLocalStore userLocalStore;
    private static final String TAG = "landingPageLogout";

    /************************************
     * onCreate activity_landing_page
     ************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_landing_page);
    }

    /************************************
     * onClick for LandingPage's
     * LogOut option
     ************************************/
    //@Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.logoutButton:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                Log.i(TAG, "LandingPage user state changed to logged out.");
                startActivity(new Intent(this, Login.class));
                Log.i(TAG, "Login Activity called from LandingPage.");
                break;
            default:
                break;
        }
    }

}