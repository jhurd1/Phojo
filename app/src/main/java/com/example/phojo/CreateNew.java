package com.example.phojo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * CREATENEW
 * A class employed for the
 * synthesizing of new Phojo
 * listing.
 *
 * @author edgarcobian
 * @author chrisfowler
 * @author danallewellyn
 * @author jamiehurd
 */
public class CreateNew extends AppCompatActivity implements View.OnClickListener {

    /**********************************
     * DATA MEMBERS
     ********************************/
    public static final String TAG = "checkTitleBar";
    Button addDescription, addImages, selectCategory, publish;

    // data members used for addImages button functionality
    // Uri is not used yet...........................
    private static final int PICK_IMAGE = 100;
    Uri selectedImageURI;

    /**********************************
     * onCreate for CreateNew
     * Creates the objects necessary
     * for a new Phojo creation
     * @param savedInstanceState
     ********************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new);

        // hide the title bar
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
            Log.d(TAG, "hide title bar failed for createNew.java");
        }

        addDescription = (Button) findViewById(R.id.addDescription);
        addImages = (Button) findViewById(R.id.addImages);
        selectCategory = (Button) findViewById(R.id.selectCategory);
        publish = (Button) findViewById(R.id.publish);

        publish.setOnClickListener(this);
    }

    /********************************
     * onClick for CreateNew's
     * Phojo data upload options
     * @param v
     *******************************/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.publish:
                Intent openShareRecentActivity = new Intent(this, ShareRecent.class);
                openShareRecentActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(openShareRecentActivity, 0);
                break;
        }
    }

    // we can move openGallery() into onClick with a switch statement that determines if addImages was clicked
    // I just have it here for now...
    public void onAddImages(View v) {
        openGallery();
    }

    /***********************************
     * openGallery for addImages button
     **********************************/
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        // I don't know if this is the right method to open the intent in an activity??
        // we do have to start the intent in an activity and then somehow get the image uri back
        // if we use startActivityForResult, then we can override onActivityResult and get the data there
        // however, when I try to implement that, it is making the app crash..........
        startActivityForResult(gallery, PICK_IMAGE);
    }
}