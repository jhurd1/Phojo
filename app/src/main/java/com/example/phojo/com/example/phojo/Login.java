package com.example.phojo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener {

    /*****************************
     * DATA MEMBERS
    ****************************/
    Button bLogin;
    EditText etUsername, etPassword;
    TextView tvRegisterLink;
    UserLocalStore userLocalStore;

    /*****************************
     * onCreate
     * initialize the activity
     * load data from 'savedInstanceState'
     * @param savedInstanceState
     * Bundle stores activity data.
     ****************************/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // super accesses members from the parent
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);
    }

    /*****************************
     * onClick event
     * triggered by user click
     * handle each button (case)
     * @param v
     ****************************/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogin:
                User user = new User();
                userLocalStore.storeUserData(user);
                userLocalStore.setUserLoggedIn(true);
                startActivity(new Intent(this, CreateNewShareRecent.class));
                break;

            case R.id.tvRegisterLink:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }
}