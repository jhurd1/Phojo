package com.example.phojo;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.multidex.MultiDex;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * LOGIN
 * A class for managing the login
 * of users into the Phojo app.
 *
 * @author edgarcobian
 * @author chrisfowler
 * @author danallewellyn
 * @author jamiehurd
 */
public class Login extends AppCompatActivity implements View.OnClickListener {

    /*****************************
     * DATA MEMBERS
    ****************************/
    Button bLogin;
    EditText etUsername;
    EditText etPassword;
    TextView tvRegisterLink;
    UserLocalStore userLocalStore;
    TextView stylePhojoHeader;
    private static final String TAG = "createAccount";
    private FirebaseAuth mAuth;
    //Register r = new Register();

    /*****************************
     * ACCESSORS
     ****************************/
    public EditText getEtPassword()
    {
        return etPassword;
    }

    /*****************************
     * MULTI_DEX_REQUIRED
     * FireBase led to the
     * breach of 64k function
     * references.
     ****************************/
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /*****************************
     * onCreate
     * initialize the login activity
     * load data from 'savedInstanceState'
     * @param savedInstanceState
     * Bundle stores activity data.
     ****************************/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //mAuth = FirebaseAuth.getInstance();
        //onStart(); // see if the user is signed in; this caused a segfault/null reference

        // super accesses members from the parent
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // hides the title bar
        getSupportActionBar().hide();

        // Control the font style for the header
        stylePhojoHeader = (TextView) findViewById(R.id.textView12);
        Context context = this;
        stylePhojoHeader.setTypeface(ResourcesCompat.getFont(context, R.font.balloonpopsax0x));
        
        // push user input into the user object
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        mAuth = FirebaseAuth.getInstance(); // getInstane() likely auto references firebase JSON file

        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);
    }

    /*****************************
     * onStart()
     * check to see if the user
     * is currently signed in.
     ****************************/
    @Override
    public void onStart()
    {
        super.onStart();

        FirebaseUser current = mAuth.getCurrentUser();
        if(current != null)
        {
            reload();
        }
    }

    /*****************************
     * reload()
     * Reloads the activity upon
     * current user check failure.
     ****************************/
    private void reload() // called by onStart()
    {
        finish();
        startActivity(getIntent());
    }

    /*****************************
     * onClick login event
     * triggered by user click
     * handle each button (case)
     * @param v
     ****************************/
    @Override
    public void onClick(View v)
    {
        /*String email = r.getEmail();
        String password = r.getPassword();*/
        switch (v.getId())
        {
            case R.id.bLogin:
                User user = new User(etUsername, etPassword);
                userLocalStore.storeUserData(user);
                userLocalStore.setUserLoggedIn(true);
                //signIn(email, password); // sign in with firebase
                startActivity(new Intent(this, ShareRecent.class));
                break;

            case R.id.tvRegisterLink:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }

    /*****************************
     * updateUI
     * as adapted from firebase
     * THIS CODE IS REPEATED IN
     * THE PROGRAM SO CAN BE
     * ADDED TO NEW BASE CLASS
     * @param user
     ****************************/
    private void updateUI(FirebaseUser user)
    {

    }

    /*****************************
     * signIn event
     * as adapted from firebase
     * @param email, password
     ****************************/
    private void signIn(String email, String password)
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }

}