package com.example.phojo;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.multidex.MultiDex;
import java.util.Arrays;
import java.util.List;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
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
    private static final int RC_SIGN_IN = 123;


    /*****************************
     * Sign_in Intent
     *
     ****************************/
    //sign in as adapted from
    // https://firebase.google.com/docs/auth/android/firebaseui
    public void signInIntent()
    {
        List<AuthUI.IdpConfig> signInMethods = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(signInMethods)
                .build(), RC_SIGN_IN);
    }

    /*****************************
     * onActivityResult()
     *
     ****************************/
    @Override
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

        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);
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