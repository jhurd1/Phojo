package com.example.phojo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * CREATENEWSHARERECENT
 * A class to handle the
 * sharing of recent Phojo
 * listings by thumbnail.
 *
 * @author edgarcobian
 * @author chrisfowler
 * @author danallewellyn
 * @author jamiehurd
 */
public class ShareRecent extends AppCompatActivity implements View.OnClickListener {

    /************************************
     * DATA MEMBERS
     ************************************/
    Button logoutButton, cButton, bEditProfile;
    TextView tvShare;
    UserLocalStore userLocalStore;
    private static final String TAG = "landingPageLogout";

    /************************************
     * onCreate
     * Removes title bar
     * creates necessaries
     * @param savedInstanceState
     ************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide the title bar
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
            Log.d(TAG, "hide title bar failed for createNewShareRecent.java");
        }
        setContentView(R.layout.activity_share_recent);
        logoutButton = (Button) findViewById(R.id.logoutButton);
        cButton = (Button) findViewById(R.id.cButton);
        tvShare = (TextView) findViewById(R.id.tvShare);
        bEditProfile = (Button)findViewById(R.id.bEditProfile);

        bEditProfile.setOnClickListener(this);
        logoutButton.setOnClickListener(this);
        cButton.setOnClickListener(this);
        tvShare.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);
    }

    /************************************
     * LOGOUT
     * handles logging out, createNew,
     * and editProfile.
     * @param v
     ************************************/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bEditProfile:
                startActivity(new Intent (this, EditProfile.class));
                break;
            case R.id.cButton:
                startActivity(new Intent(this, CreateNew.class));
                break;
            case R.id.logoutButton:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                Log.i(TAG, "User state changed to logged out.");
                startActivity(new Intent(this, Login.class));
                Log.i(TAG, "Login Activity called.");
                break;
            default:
                break;
        }
    }
}