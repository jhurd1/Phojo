package com.example.phojo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import "UserValidatorTest.java";

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    UserValidatorTest uvt = new UserValidatorTest();
    Button bLogout;
    EditText etFirstName, etMiddleName, etLastName, etUsername, uTag;
    UserLocalStore userLocalStore;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etMiddleName = (EditText) findViewById(R.id.etMiddleName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        bLogout = (Button) findViewById(R.id.bLogout);
        uTag = (EditText) findViewById(R.id.uTag);

        bLogout.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (authenticate() == true) {
            displayUserDetails();
        }
    }

    private void displayUserDetails() {
        User user = userLocalStore.getLoggedInUser();
        UserValidatorTest.userValidatorReturnsTrue();
        etFirstName.setText(user.firstname);
        etMiddleName.setText(user.middleinitial);
        etLastName.setText(user.lastname);
        etUsername.setText(user.username);
        uTag.setText(user.userTag);
    }

    private boolean authenticate() {
        return userLocalStore.getUserLoggedIn();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                Log.i(TAG, "User state changed to logged out.");
                startActivity(new Intent(this, Login.class));
                Log.i(TAG, "Login Activity called.");
                break;
            default:
                break;
        }
    }

    public String getTestString()
    {
        String testString = "";
        return testString;
    }

    public View getActivity()
    {
        /*Context c = null;
        return c;*/
        View view = null;
        return view;
    }

    //The below code is commented out because it is for testing or it is redundant. I had put the shared preferences code in the UserLocalStore.java
    /*private final String testString = "test real string";

        public MainActivity(Context context)
        {
        }

        /*public MainActivity()
        {

        }

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

        public String getHelloWorldString()
        {
            return testString;
        }


    SharedPreferences sharedPreferences = getSharedPreferences ("userData", Context.MODE_PRIVATE);
    }
    private static final String FIRST_NAME = "";
    private static final String LAST_NAME = "";
    private static final String EMAIL = "";
    private static final String PASSWORD = "";
    private static final String TAG = "";
    Context context = getActivity(); // context lets new objects in on the program's state
    Resources res = getResources();
    String[] userData = res.getStringArray(R.array.preference_file_key);
    SharedPreferences sp = context.getSharedPreferences(
            String.valueOf(res.getStringArray(R.array.preference_file_key)), Context.MODE_PRIVATE);

    private Context getActivity()
    {

    }

    protected void onClick(Context c) // changed to context from view
    {
        SharedPreferences.Editor edit = sp.edit();

        //edit.putString(FIRSTNAME, ""); // replace "" with a variable holding the user's input
        //edit.putString(LASTNAME, ""); // replace "" with a variable holding the user's input
        edit.putString(EMAIL, ""); // replace "" with a variable holding the user's input
        edit.putString(PASSWORD, ""); // replace "" with a variable holding the user's input
        //edit.putString(TAG, ""); // replace "" with a variable holding the user's input
        edit.apply();
    }

    SharedPreferences loadSharedPreferences = getSharedPreferences("preference_file_key",
            Context.MODE_PRIVATE);
    EMAIL = loadSharedPreferences.getString("username", "");
    PASSWORD = loadSharedPreferences.getString("password", "");
*/
}