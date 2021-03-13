package com.example.phojo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity implements View.OnClickListener {

    /**********************************
     * DATA MEMBERS
     ********************************/
    public static final String TAG = "checkTitleBar";
    Button bRegister;
    EditText etFirstName, etMiddleName, etLastName, etUsername, etPassword, uTag;
    //private static final String TAG2 = "RegisterActivity";

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

        etFirstName = (EditText) findViewById(R.id.etFirstName) ;
        etMiddleName = (EditText) findViewById(R.id.etMiddleName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);
        uTag = (EditText) findViewById(R.id.uTag);

        bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bRegister:
                String firstname = etFirstName.getText().toString();
                String middleinitial = etMiddleName.getText().toString();
                String lastname = etLastName.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String userTag = uTag.getText().toString();

                User registeredData = new User(firstname, middleinitial, lastname, username, password, userTag);
                Log.i(TAG, "User object instantiated from Register.java.");

                startActivity(new Intent(this, Login.class));

                break;

        }
    }
}