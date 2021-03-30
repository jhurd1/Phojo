package com.example.phojo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class Register extends AppCompatActivity implements View.OnClickListener
{

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

    private FirebaseDatabase myDB;
    private DatabaseReference myDBref;


    public static String firstname;
    public static String middleinitial;
    public static String lastname;
    public static String email;
    public static String password;
    public static String userTag;

    /**********************************
     * CONSTRUCTORS
     ********************************/

    /**********************************
     * Default
     * Needed for Firebase
     * An empty constructor
     ********************************/
    public Register()
    {

    }

    /**********************************
     * Non-default
     * passes in data members
     ********************************/
    public Register(Button bRegister,
            EditText etFirstName,
            EditText etMiddleName,
            EditText etLastName,
            EditText etUsername,
            EditText etPassword,
            EditText uTag)
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

    /*********************************
     * onCreate for Register.java
     * Build the necessary objects
     * for registration to happen.
     * @param savedInstanceState
     ********************************/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // database pieces
        myDB = FirebaseDatabase.getInstance();
        myDBref = myDB.getReference().child("PhojoDB"); // name of the table to be instantiated

        // hide the title bar
        try
        {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e)
        {
            Log.d(TAG,"hide title bar failed for createNew.java");
        }

        //setContentView(R.layout.activity_register); // need this still with a menu item?
        //setContentView(R.menu.menu_activity_register);

        etFirstName = findViewById(R.id.etFirstName);
        etMiddleName = findViewById(R.id.etMiddleName);
        etLastName = findViewById(R.id.etLastName);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        bRegister = findViewById(R.id.bRegister);
        uTag = findViewById(R.id.uTag);

        /*etFirstName = (EditText) findViewById(R.id.etFirstName) ;
        etMiddleName = (EditText) findViewById(R.id.etMiddleName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);
        uTag = (EditText) findViewById(R.id.uTag);*/

        bRegister.setOnClickListener(this);
    }

    /***********************************
     * onOptionsItemSelected
     * effectively what onClick does
     * but testing to see if this will
     * actually save data to DB
     * because right now onClick isn't
     **********************************/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) // replace with EditText
    // as this is what corresponds to register.xml items
    {
        switch(item.getItemId())
        {
            case R.id.etFirstName:
            case R.id.etMiddleName:
            case R.id.etLastName:
            case R.id.etUsername:
            case R.id.etPassword:
            case R.id.uTag:
                saveData();
                Toast.makeText(Register.this, "Testing save.",
                        Toast.LENGTH_LONG).show();
                clean();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /***********************************
     * onCreateOptionsMenu
     **********************************/
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_register, menu);
        return true;
    }

    /***********************************
     * saveData
     **********************************/
    private void saveData()
    {
        firstname = etFirstName.getText().toString();
        middleinitial = etMiddleName.getText().toString();
        lastname = etLastName.getText().toString();
        email = etUsername.getText().toString();
        password = etPassword.getText().toString();
        userTag = uTag.getText().toString();

        User user = new User(firstname, middleinitial, lastname, email, password, userTag);

        myDBref.push().setValue(user); // need to instance a new object above and pass in here
    }

    /***********************************
     * clean()
     **********************************/
    private void clean()
    {
        etFirstName.setText("");
        etMiddleName.setText("");
        etLastName.setText("");
        etUsername.setText("");
        etPassword.setText("");
        uTag.setText("");
        etFirstName.requestFocus(); // place focus on the first field
    }

    /*********************************
     * Enforcer()
     * Enforce password requirements
     * exit the loop upon finding a
     * digit and special char.
     * If no digit and char is found,
     * the flag remains false and
     * the password fails.
     ********************************/
    public boolean enforcePassword()
    {
        password = etPassword.getText().toString(); //moving these more global
        System.out.println("Password is " + password); // confirm password is passed in

        if(password.length()>=8)
        {
            Pattern letter = Pattern.compile("[a-zA-z]");
            Pattern digit = Pattern.compile("[0-9]");
            Pattern special = Pattern.compile ("[!@#$%&*]");


            Matcher hasLetter = letter.matcher(password);
            Matcher hasDigit = digit.matcher(password);
            Matcher hasSpecial = special.matcher(password);

            return hasLetter.find() && hasDigit.find() && hasSpecial.find();

        }
        else
            return false;
    }

    /*********************************
     * testPassword()
     * sets parameters
     * calls User enforcePassword()
     ********************************/
    public boolean testPassword()
    {
        boolean passes = true;

        password = etPassword.getText().toString();
        System.out.println("Password is " + password); // confirm password is passed in

        email = etUsername.getText().toString();
        System.out.println("Email is " + email); // confirm email is passed in

        for(int i = 0; i < password.length(); i++)
        {
            System.out.println("Password is still confirmed as: " + password);
            if(!enforcePassword())
            {
                // exit and send a message
                passes = false;
                Context context = getApplicationContext();
                CharSequence text = "Password failed complexity test.";
                int duration = Toast.LENGTH_LONG;
                Toast toast = makeText(context, text, duration);
                toast.show();
                Log.i(TAG, "Password failed requirements.");
                clean();

                break;
            } else // continue with the registration
            {
                passes = true;
                //createAccount(email, password); // create the object in firebase  <----causing app crash<----
                //saveData(); // save the object in firebase DB                     <----causing app crash<----
                Toast.makeText(Register.this, "Info saved.",
                        Toast.LENGTH_SHORT).show();
                Log.i(TAG, "User object instantiated from Register.java.");
                //clean();                                                          <----causing password to fail even if it is a valid password<----
            }

        }
        return passes;
    }

    /*********************************
     * onClick for register
     * Finalize registration
     * passing in user's registration
     * information.
     * @param view
     ********************************/
    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.bRegister:

                //test password before continuing with registration
                if(testPassword())
                {
                    startActivity(new Intent(this, Login.class));
                } else {
                    break;
                }
            default:
                break;
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
   /* private void createAccount(String email, String password)
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
    }*/
}