package com.example.phojo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

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
    private static final String TAG = "Create New Activity";
    Button addPhoto, publishNow;
    ImageView photo1, photo2, photo3;

    // data members used for addImages button functionality
    private static final int PICK_IMAGE = 100;
    Uri photo1URI, photo2URI, photo3URI;

    /**********************************
     * onCreate for CreateNew
     * Creates the objects necessary
     * for a new Phojo creation
     * @param savedInstanceState
     ********************************/
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new);

        // hide the title bar
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
            Log.d(TAG, "hide title bar failed for CreateNew.java");
        }

        addPhoto = (Button) findViewById(R.id.addPhoto);
        publishNow = (Button) findViewById(R.id.publishNow);

        publishNow.setOnClickListener(this);
        addPhoto.setOnClickListener(this);

        photo1 = (ImageView) findViewById(R.id.photo1);
        photo2 = (ImageView) findViewById(R.id.photo2);
        photo3 = (ImageView) findViewById(R.id.photo3);

        // START of code for default category dropdown list
        String[] defaultCategories ={"vacation", "date night", "birthday party", "family reunion"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, defaultCategories);
        AutoCompleteTextView category =  (AutoCompleteTextView)findViewById(R.id.category);
        category.setAdapter(adapter); //setting the adapter data into the AutoCompleteTextView
        category.setOnTouchListener(new View.OnTouchListener() { //shows default categories when category is selected
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                category.showDropDown();
                return false;
            }
        });
        // END of code for default category dropdown list

    }

    /********************************
     * onClick for CreateNew's
     * Phojo data upload options
     * @param v
     *******************************/
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

    /***********************************
     * openGallery for addImages button
     **********************************/
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){

            if(photo1URI==null) {
                photo1URI = data.getData();
                photo1.setImageURI(photo1URI);
            } else if (photo2URI==null) {
                photo2URI = data.getData();
                photo2.setImageURI(photo2URI);
            } else {
                photo3URI = data.getData();
                photo3.setImageURI(photo3URI);
            }
        }
    }
}