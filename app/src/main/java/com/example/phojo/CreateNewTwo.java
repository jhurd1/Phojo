package com.example.phojo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateNewTwo extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Create New Two Activity";
    Button addPhoto, publishNow;
    ImageView photo1, photo2, photo3;

    // data members used for addImages button functionality
    private static final int PICK_IMAGE = 100;
    Uri photo1URI, photo2URI, photo3URI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_two);

        // hide the title bar
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
            Log.d(TAG, "hide title bar failed for CreateNewTwo.java");
        }

        addPhoto = (Button) findViewById(R.id.addPhoto);
        publishNow = (Button) findViewById(R.id.publishNow);

        publishNow.setOnClickListener(this);
        addPhoto.setOnClickListener(this);

        photo1 = (ImageView) findViewById(R.id.photo1);
        photo2 = (ImageView) findViewById(R.id.photo2);
        photo3 = (ImageView) findViewById(R.id.photo3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.publishNow:
                // we will need to add functionality here to get the category and description
                // and the saved image Uri's.................................................
                Intent openShareRecentActivity = new Intent(this, ShareRecent.class);
                openShareRecentActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(openShareRecentActivity, 0);
                break;
            case R.id.addPhoto:
                openGallery();
                break;
            default:
                break;
        }
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        // I don't know if this is the right method to open the intent in an activity??
        // we do have to start the intent in an activity and then somehow get the image uri back
        // if we use startActivityForResult, then we can override onActivityResult and get the data there
        // however, when I try to implement that, it is making the app crash..........
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            photo1URI = data.getData();
            photo1.setImageURI(photo1URI);
        }
    }
}