package com.example.phojo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends BaseActivity implements View.OnClickListener {

    Button bLogin;
    EditText etUsername, etPassword;
    TextView tvRegisterLink;

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogin:
                // Fetch data from shared storage
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                LoginUserResult storedUser = UserLocalStore.loginUser(this, username, password);
                if(storedUser.isError()){
                    showError(v, storedUser.getMessage());
                } else {
                    User user = storedUser.getUser();
                    userLocalStore.storeUserData(user);
                    userLocalStore.setUserLoggedIn(true);
                    showSuccess(v, storedUser.getMessage());
                }
                break;
            case R.id.tvRegisterLink:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }
}