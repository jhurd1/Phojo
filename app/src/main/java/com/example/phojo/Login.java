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
    EditText etUsername, etPassword;
    TextView tvRegisterLink;
    UserLocalStore userLocalStore;
    TextView stylePhojoHeader;
    private static final String TAG = "createAccount";
    private FirebaseAuth mAuth;

    /*****************************
     * Sign_in Intent
     * commented out for now
     * as another solution may
     * prove more desirable.
     ****************************/
    //sign in as adapted from
    // https://firebase.google.com/docs/auth/android/firebaseui
/*    public void signInIntent()
    {
        List<AuthUI.IdpConfig> signInMethods = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(signInMethods)
                .build(), RC_SIGN_IN);
    }*/

    /*****************************
     * onActivityResult()
     * commented out for now
     *      as another solution may
     *      prove more desirable.
     ****************************/
  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }*/

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
        mAuth = FirebaseAuth.getInstance(); // initialize the FireBaseAuth instance

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
    private void reload()
    {
        finish();
        startActivity(getIntent());
    }

    /*****************************
     * createAccount()
     * take email/password
     * validate credentials
     * create a new user
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
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    /*****************************
     * updateUI event
     * just call reload() instead?
     * @param user
     ****************************/
    private void updateUI(FirebaseUser user)
    {
        // need a broadcast receiver?
    }

    /*****************************
     * onClick login event
     * triggered by user click
     * handle each button (case)
     * @param v
     ****************************/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogin:
                User user = new User(etUsername, etPassword);
                userLocalStore.storeUserData(user);
                userLocalStore.setUserLoggedIn(true);
                startActivity(new Intent(this, ShareRecent.class));
                break;

            case R.id.tvRegisterLink:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }
}