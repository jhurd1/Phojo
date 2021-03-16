package com.example.phojo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditProfile extends AppCompatActivity implements View.OnClickListener {

    /**********************************
     * DATA MEMBERS
     ********************************/
    public static final String TAG = "checkTitleBar";
    Button eProfile;
    EditText edFirstName, edMiddleName, edLastName, edUsername, edPassword, edTag;

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

                Intent openCreateNewShareRecentActivity = new Intent(this, CreateNewShareRecent.class);
                openCreateNewShareRecentActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(openCreateNewShareRecentActivity, 0);

                break;
        }
    }
}

