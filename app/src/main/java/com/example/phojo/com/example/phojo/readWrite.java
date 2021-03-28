package com.example.phojo.com.example.phojo;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.phojo.Register;
import com.example.phojo.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.makeText;

public class readWrite
{
    // singleton pattern for constants
    /**********************************
     * DATA MEMBERS
     ********************************/
    private FirebaseDatabase myDB; // for saving to DB
    private DatabaseReference myDBref; // for saving to DB
    private FirebaseAuth mAuth; // authentication object for Firebase
    private String TAG = "readWriteTag";
    /********************************
     * CONSTRUCTORS
     ********************************/
    /*private readWrite() // secure constructor
    {

    }*/
    /**********************************
     * OTHER MEMBERS
     * @param u
     ********************************/
    public void writeUser(User u)
    {
        myDB = FirebaseDatabase.getInstance();
        myDBref = myDB.getReference().child("phojoDB"); // string needs to match a backend reference?
        myDBref.push().setValue(u);
        /*mAuth.createUserWithEmailAndPassword(u.getEmail(), u.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.

                        }
                    }
                    });*/
    }
}
