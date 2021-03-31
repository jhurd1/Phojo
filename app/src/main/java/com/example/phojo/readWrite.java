package com.example.phojo;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.phojo.Register;
import com.example.phojo.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.makeText;

/**
 * READ_WRITE Class
 * A class whose methods will manage
 * all database read-writes
 * to Firebase.
 */
public class readWrite extends AppCompatActivity
{
    /**********************************
     * DATA MEMBERS
     ********************************/
    private FirebaseDatabase myDB; // for saving to DB
    private DatabaseReference myDBref; // for saving to DB
    private FirebaseAuth mAuth; // authentication object for Firebase
    private ChildEventListener listen;
    private String TAG = "readWriteTag";
    Register r = new Register();
    Map<String, User> users = new HashMap<>(); // user or register here?
    Map<String, Register> reg = new HashMap<>();

    /********************************
     * CONSTRUCTORS
     ********************************/
    public readWrite()
    {
        //myDBref.child("users"); // db ref doc: string passed in is path
    }

    /***********************************
     * testSave()
     * instead of using menu item
     * use strings or editTexts or both
     **********************************/
    public void testSave()
    {
        myDB = FirebaseDatabase.getInstance();
        myDBref = myDB.getReference("PhojoDB");

        //users.put(r.etFirstName, new User());
        reg.put(r.firstname,
                new Register(r.etFirstName,
                r.etMiddleName,
                r.etLastName,
                r.etUsername,
                r.etPassword,
                r.uTag));
        reg.put(r.middleinitial,
                new Register(r.etFirstName,
                        r.etMiddleName,
                        r.etLastName,
                        r.etUsername,
                        r.etPassword,
                        r.uTag));
        reg.put(r.lastname,
                new Register(r.etFirstName,
                        r.etMiddleName,
                        r.etLastName,
                        r.etUsername,
                        r.etPassword,
                        r.uTag));
        reg.put(r.email,
                new Register(r.etFirstName,
                        r.etMiddleName,
                        r.etLastName,
                        r.etUsername,
                        r.etPassword,
                        r.uTag));
        reg.put(r.password,
                new Register(r.etFirstName,
                        r.etMiddleName,
                        r.etLastName,
                        r.etUsername,
                        r.etPassword,
                        r.uTag));
        reg.put(r.userTag,
                new Register(r.etFirstName,
                        r.etMiddleName,
                        r.etLastName,
                        r.etUsername,
                        r.etPassword,
                        r.uTag));
        Toast.makeText(readWrite.this, "Testing save.",
                Toast.LENGTH_LONG).show();
        myDBref.setValue(reg);
        clean();
    }

    /***********************************
     * onOptionsItemSelected
     * looks specific to menu item
     **********************************/
    /*@Override
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
                Toast.makeText(readWrite.this, "Testing save.",
                        Toast.LENGTH_LONG).show();
                clean();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    /***********************************
     * onCreateOptionsMenu
     * this is for instantiating
     * a menu object
     * Still need it?
     **********************************/
   /* public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_register, menu);
        return true;
    }*/

    /***********************************
     * saveData
     * save data members from register
     * passing them in to a user object
     **********************************/
    public void saveData()
    {
        r.firstname = r.etFirstName.getText().toString();
        r.middleinitial = r.etMiddleName.getText().toString();
        r.lastname = r.etLastName.getText().toString();
        r.email = r.etUsername.getText().toString();
        r.password = r.etPassword.getText().toString();
        r.userTag = r.uTag.getText().toString();

        User user = new User(r.firstname, r.middleinitial, r.lastname, r.email, r.password, r.userTag);

        myDBref.push().setValue(user);
    }

    /***********************************
     * clean()
     **********************************/
    public void clean()
    {
        r.etFirstName.setText("");
        r.etMiddleName.setText("");
        r.etLastName.setText("");
        r.etUsername.setText("");
        r.etPassword.setText("");
        r.uTag.setText("");
        r.etFirstName.requestFocus(); // place focus on the first field
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
  /*  public void updateUI(FirebaseUser user)
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
    }*/

    /*****************************
     * createAccount()
     * take email/password
     * validate credentials
     * create a new user in fireb.
     * as adapted from firebase
     *      codebase
     ****************************/
    /*private void createAccount(String email, String password)
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
            makeText(readWrite.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
            updateUI(null);
        }
    }*/
}
