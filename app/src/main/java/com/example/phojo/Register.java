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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.widget.Toast.makeText;

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
    private FirebaseAuth mAuth;
    private String firstname;
    private String middleinitial;
    private String lastname;
    private String email;
    private String password;
    private String userTag;

    /**********************************
     * CONSTRUCTORS
     ********************************/
    /**********************************
     * Default
     ********************************/
    public Register()
    {
        String test = "";
    }
    /**********************************
     * Non-default
     * passes in data members
     ********************************/
    public Register(EditText etFirstName, EditText etMiddleName,
                    EditText etLastName, EditText etUsername, EditText etPassword, EditText uTag,
                    User user)
    {
        this.bRegister = bRegister;
        this.etFirstName = etFirstName;
        this.etMiddleName = etMiddleName;
        this.etLastName = etLastName;
        this.etUsername = etUsername;
        this.etPassword = etPassword;
        this.uTag = uTag;
        this.user = user;
    }

    /********************************
     * ACCESSORS
     *******************************/
    public EditText getEtPassword()
    {
        return etPassword;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
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
                firstname = etFirstName.getText().toString();
                middleinitial = etMiddleName.getText().toString();
                lastname = etLastName.getText().toString();
                email = etUsername.getText().toString();
                password = etPassword.getText().toString();
                userTag = uTag.getText().toString();
                //test password before continuing with registration
                for(int i = 0; i < etPassword.length(); i++)
                {
                    if(etPassword.length() < 8 || user.enforcePassword(passCheck) == false)
                    {
                        // exit and send a message
                        Context context = getApplicationContext();
                        CharSequence text = "Password failed complexity test.";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = makeText(context, text, duration);
                        toast.show();
                        Log.i(TAG, "Password failed requirements.");
                        break;
                    } else // continue with the registration
                    {
                        User registeredData = new User(firstname, middleinitial, lastname, email,
                                password, userTag);
                        createAccount(email, password); // create the object in firebase
                        Log.i(TAG, "User object instantiated from Register.java.");
                        startActivity(new Intent(this, Login.class));
                        break; // is this break necessary?
                    }
                }
        }
    }

    /*****************************
     * updateUI event
     *
     * we're already calling start-
     * activity in onClick!!!
     *
     * @param user
     * adapted from firebase
     ****************************/
    private void updateUI(FirebaseUser user)
    {
        //hideProgressBar();
        if(user != null)
        {
            Toast.makeText(this, "Registration successful.",
                    Toast.LENGTH_SHORT).show();
        } else
        {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_LONG);
        }
    }

    /*****************************
     * createAccount()
     * take email/password
     * validate credentials
     * create a new user in fireb.
     * as adapted from firebase
     *      codebase
     ****************************/
    private void createAccount(String email, String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "Register success!");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "Register failed!", task.getException());
                            makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }
}