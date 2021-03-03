package com.example.phojo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /*private final String testString = "test real string";
    private static final String FIRSTNAME = "";
    private static final String LASTNAME = "";
    private static final String EMAIL = "";
    private static final String PASSWORD = "";
    private static final String TAG = "";

    public MainActivity(Context context) {
    }

    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public String getTestString() {
        return testString;
    }

    public String getHelloWorldString() {
        return testString;
    }

    public View getActivity() {
        View view = null;
        return view;
    }
    protected void onClick(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences ("userData", Context.MODE_PRIVATE);
    }*/

    Button bLogout;
    EditText etFirstName, etMiddleName, etLastName, etUsername;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etMiddleName = (EditText) findViewById(R.id.etMiddleName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        bLogout = (Button) findViewById(R.id.bLogout);

        bLogout.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(authenticate() == true) {
            displayUserDetails();
        }
    }

    private void displayUserDetails() {
        User user = userLocalStore.getLoggedInUser();

        etFirstName.setText(user.firstname);
        etMiddleName.setText(user.middleinitial);
        etLastName.setText(user.lastname);
        etUsername.setText(user.username);
    }

    private boolean authenticate(){
        return userLocalStore.getUserLoggedIn();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(this, Login.class));
                break;
        }
    }
}
