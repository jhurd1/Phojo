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
     * onCreate
     * @param savedInstanceState
     * Removes title bar
     * creates necessaries
     ************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // hide the title bar
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e)
        {
            Log.d(TAG,"hide title bar failed for createNewShareRecent.java");
        }
        setContentView(R.layout.create_new_share_recent);
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

    @Override
    public void onClick(View v) {

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