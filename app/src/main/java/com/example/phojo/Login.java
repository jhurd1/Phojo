package com.example.phojo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.multidex.MultiDex;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.w3c.dom.Text;

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
        /*stylePhojoHeader = (TextView) findViewById(R.id.phojoHeader);
        Context context = this;
        stylePhojoHeader.setTypeface(ResourcesCompat.getFont(context, R.font.balloonpopsax0x));*/
        
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