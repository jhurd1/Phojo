package com.example.phojo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * EDITPROFILE
 * A class for controlling
 * the editing of a user's profile.
 *
 * @author edgarcobian
 * @author chrisfowler
 * @author danallewellyn
 * @author jamiehurd
 */
public class EditProfile extends AppCompatActivity implements View.OnClickListener {

    /**********************************
     * DATA MEMBERS
     ********************************/
    public static final String TAG = "checkTitleBar";
    Button eProfile;
    EditText edFirstName, edMiddleName, edLastName, edUsername, edPassword, edTag;

    /********************************
     * editProfile's onCreate
     * Synthesizes objects necessary for editProfile
     * @param savedInstanceState
     *******************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide the title bar
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e)
        {
            Log.d(TAG,"hide title bar failed for createNew.java");
        }
        setContentView(R.layout.activity_register);

        edFirstName = (EditText) findViewById(R.id.edFirstName);
        edMiddleName = (EditText) findViewById(R.id.edMiddleName);
        edLastName = (EditText) findViewById(R.id.edLastName);
        edUsername = (EditText) findViewById(R.id.edUsername);
        edPassword = (EditText) findViewById(R.id.edPassword);
        eProfile = (Button) findViewById(R.id.eProfile);
        edTag = (EditText) findViewById(R.id.edTag);

        eProfile.setOnClickListener(this);
    }

    /*********************************
     * onClick for editProfile
     * passes in data from users to edit
     * their profile
     * @param v
     ********************************/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.eProfile:
                String firstname = edFirstName.getText().toString();
                String middleinitial = edMiddleName.getText().toString();
                String lastname = edLastName.getText().toString();
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                String userTag = edTag.getText().toString();

                User editData = new User(firstname, middleinitial, lastname, username, password, userTag);

                Intent openShareRecentActivity = new Intent(this, ShareRecent.class);
                openShareRecentActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(openShareRecentActivity, 0);

                break;
        }
    }
}

