package com.example.phojo;

import android.os.Bundle;
import android.util.Log;
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

    /********************************
     * CONSTRUCTORS
     ********************************/
    public readWrite()
    {

    }

    /**********************************
     * onCreate()
     ********************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list);

        myDB = FirebaseDatabase.getInstance();
        myDBref = myDBref.getRef().child("PhojoDB");
        listen = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName)
            {
                // assign a generic tv to one from
                // another class?
                //TextView tvData = findViewById(R.id.tvData);

                Login login = snapshot.getValue(Login.class);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName)
            {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot)
            {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName)
            {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        };

        myDBref.addChildEventListener(listen);
    }

}
