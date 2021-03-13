package com.example.phojo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class CreateNewShareRecent extends AppCompatActivity implements View.OnClickListener {

    /************************************
     * PRIVATE MEMBERS
     ************************************/
    Button logoutButton, cButton;
    TextView tvShare;

    UserLocalStore userLocalStore;
    private static final String TAG = "landingPageLogout";

    /************************************
     * onCreate activity_landing_page
     ************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e)
        {
            Log.d(TAG,"hide title bar failed");
        }
        setContentView(R.layout.create_new_share_recent);
        Log.i(TAG, "onCreate called for createNewShareRecent.java.");
        //Remove the banner at page top.
        //the following line and @android/style:theme.NoTitleBar in
        // the manifest.xml crashed the app without notice of error
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

        logoutButton = (Button)findViewById(R.id.logoutButton);
        cButton = (Button)findViewById(R.id.cButton);
        tvShare = (TextView)findViewById(R.id.tvShare);

        logoutButton.setOnClickListener(this);
        cButton.setOnClickListener(this);
        tvShare.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    /************************************
     * LOGOUT
     * handles logging out
     ************************************/
    //@Override
   /* public void onClick(View v) {
=======
    @Override
    public void onClick(View v) {
>>>>>>> bfac310dafafd9bcf9fdd32bebfee07fe1c4cb31
        switch (v.getId()) {
            case R.id.logoutButton:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                Log.i(TAG, "User state changed to logged out.");
                startActivity(new Intent(this, Login.class));
                Log.i(TAG, "Login Activity called.");
                break;
            case R.id.cButton:
                startActivity(new Intent(this, CreateNew.class));
            default:
                break;
        }
    }*/

}
/************************************
 * onClick for LandingPage's
 * LogOut option
 * This code isn't needed as it is redundant.

 //@Override
 public void onClick(Button b)
 {
 switch (b.getId())
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
 ************************************/