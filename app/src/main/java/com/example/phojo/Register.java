package com.example.phojo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * REGISTER
 * A class employed for managing
 * the registration of users
 * for the Phojo app.
 *
 * @author edgarcobian
 * @author chrisfowler
 * @author danallewellyn
 * @author jamiehurd
 */
public class Register extends AppCompatActivity implements View.OnClickListener {

    /**********************************
     * DATA MEMBERS
     ********************************/
    public static final String TAG = "checkTitleBar";
    Button bRegister;
    EditText etFirstName;
    EditText etMiddleName;
    EditText etLastName;
    EditText etUsername;
    EditText etPassword;
    EditText uTag;
    //private static final String TAG2 = "RegisterActivity";
    User user = new User();
    private boolean passCheck;

    /********************************
     * ACCESSORS
     *******************************/
    public EditText getEtPassword()
    {
        return etPassword;
    }

    /*********************************
     * onCreate for Register.java
     * Build the necessary objects
     * for registration to happen.
     * @param savedInstanceState
     ********************************/
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

    /*********************************
     * onClick for register
     * Finalize registration
     * passing in user's registration
     * information.
     * @param view
     ********************************/
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

                //test password before continuing with registration
                for(int i = 0; i < etPassword.length(); i++)
                {
                    if(etPassword.length() < 8 || user.enforcePassword(passCheck) == false)
                    {
                        // exit and send a message
                        Context context = getApplicationContext();
                        CharSequence text = "Password failed complexity test.";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Log.i(TAG, "Password failed requirements.");
                        break;
                    } else // continue with the registration
                    {
                        User registeredData = new User(firstname, middleinitial, lastname, username,
                                password, userTag);
                        Log.i(TAG, "User object instantiated from Register.java.");

                        startActivity(new Intent(this, Login.class));
                        break; // is this break necessary?
                    }
                }
        }
    }
}