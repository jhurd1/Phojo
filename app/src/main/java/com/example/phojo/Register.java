package com.example.phojo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.text.TextUtils;
import android.view.MenuItem;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    private boolean passCheck;
    private FirebaseAuth mAuth;

    /*private String firstname;
    private String middleinitial;
    private String lastname;
    private String email;
    private String password;
    private String userTag;*/
    private FirebaseDatabase myDB; // for saving to DB
    private DatabaseReference myDBref; // for saving to DB

    /**********************************
     * CONSTRUCTORS
     ********************************/
    /**********************************
     * Default
     ********************************/
    /*public Register()
    {

    }*/
    /**********************************
     * Non-default
     * passes in data members
     ********************************/
    public Register(/*EditText etFirstName, EditText etMiddleName,
                    EditText etLastName, EditText etUsername, EditText etPassword, EditText uTag,
                    User user*/)
    {
        this.bRegister = bRegister;
        this.etFirstName = etFirstName;
        User.firstname = etFirstName.toString();

        this.etMiddleName = etMiddleName;
        User.middleinitial = etMiddleName.toString();

        this.etLastName = etLastName;
        User.lastname = etLastName.toString();

        this.etUsername = etUsername;
        User.email = etUsername.toString();


        this.etPassword = etPassword;
        User.password = etPassword.toString();

        this.uTag = uTag;
        User.userTag = uTag.toString();

        this.user = user;
    }

    /********************************
     * MUTATORS
     *******************************/
   /* public void setFirstname(String firstname)
    {
        this.firstname = etFirstName.getText().toString();
    }

    public void setMiddleinitial(String middleinitial)
    {
        this.middleinitial = etMiddleName.getText().toString();;
    }

    public void setLastname(String lastname)
    {
        this.lastname = etLastName.getText().toString();
    }

    public void setEmail(String email)
    {
        this.email = etUsername.getText().toString();
    }

    public void setPassword(String password)
    {
        this.password = etPassword.getText().toString();
    }

    public void setUserTag(String userTag)
    {
        this.userTag = uTag.getText().toString();
    }*/

    /********************************
     * ACCESSORS
     *******************************/
    /*public EditText getEtPassword()
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

    public String getFirstname()
    {
        return firstname;
    }

    public String getMiddleinitial()
    {
        return middleinitial;
    }

    public String getLastname()
    {
        return lastname;
    }

    public String getUserTag()
    {
        return userTag;
    }*/

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

        myDB = FirebaseDatabase.getInstance();
        myDBref = myDB.getReference().child("phojoDB"); // string needs to match a backend reference?
        // hide the title bar
        try
        {
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

    /***********************************
     * onOptionsItemSelected
     * effectively what onClick does
     * but testing to see if this will
     * actually save data to DB
     * because right now onClick isn't
     **********************************/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.bRegister:
                saveData();
                Toast.makeText(Register.this, "Data saved.",
                        Toast.LENGTH_LONG).show();
                clean();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /***********************************
     * saveData()
     * saves data in the DB
     **********************************/
    private void saveData()
    {
       /* setFirstname(firstname);
        setMiddleinitial(middleinitial);
        setLastname(lastname);
        setEmail(email);
        setPassword(password);
        setUserTag(userTag);
        User u = new User(firstname, middleinitial, lastname, email,
                password, userTag);*/
        myDBref.push().setValue(user);
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
     * testPassword()
     * sets parameters
     * calls User enforcePassword()
     ********************************/
    public boolean testPassword()
    {
        boolean passes = true;

        for(int i = 0; i < User.password.length(); i++)
        {
            System.out.println("Password is " + User.password);
            if(User.password.length() < 8 || user.enforcePassword(passCheck) == false)
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
                /*User registeredData = new User(u.getFirstname(), u.getMiddleinitial(), u.getLastname(),
                        u.getEmail(),
                        password, u.getUserTag());*/
                createAccount(User.email, User.password); // create the object in firebase
                saveData(); // save the object in firebase DB
                Toast.makeText(Register.this, "Info saved.",
                        Toast.LENGTH_SHORT).show();
                Log.i(TAG, "User object instantiated from Register.java.");
                clean();

                //break; // is this break necessary?
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