package com.example.phojo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final String testString = "test real string";
    private static final String FIRSTNAME = "";
    private static final String LASTNAME = "";
    private static final String EMAIL = "";
    private static final String PASSWORD = "";
    private static final String TAG = "";
    Context context = getActivity();
    Resources res = getResources();
    String[] userData = res.getStringArray(R.array.preference_file_key);
    SharedPreferences sp = context.getSharedPreferences(
            String.valueOf(res.getStringArray(R.array.preference_file_key)), Context.MODE_PRIVATE);


    public MainActivity(Context context)
    {
    }

    public MainActivity()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public String getTestString()
    {
        return testString;
    }

    public String getHelloWorldString()
    {
        return testString;
    }

    public Context getActivity()
    {
        Context c = null;
        return c;
        /*View view = null;
        return view;*/
    }


        SharedPreferences sharedPreferences = getSharedPreferences ("userData", Context.MODE_PRIVATE);
    }

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
    public void onClick(View v){
        switch(v.getId()){
        case R.id.bLogout:
        userLocalStore.clearUserData();
        userLocalStore.setUserLoggedIn(false);
        startActivity(new Intent(this,Login.class));
        break;
        }
    }
    protected void onClick(Context c) // changed to context from view
    {
        //SharedPreferences sharedPreferences = getSharedPreferences ("userData", Context.MODE_PRIVATE);
        SharedPreferences.Editor Edit=sp.edit();
        Edit.putString(FIRSTNAME, ""); // replace "" with a variable holding the user's input
        Edit.putString(LASTNAME, ""); // replace "" with a variable holding the user's input
        Edit.putString(EMAIL, ""); // replace "" with a variable holding the user's input
        Edit.putString(PASSWORD, ""); // replace "" with a variable holding the user's input
        Edit.putString(TAG, ""); // replace "" with a variable holding the user's input
    }
}
