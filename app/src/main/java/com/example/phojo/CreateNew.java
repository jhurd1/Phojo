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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;



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
public class CreateNew extends AppCompatActivity implements View.OnClickListener
{

    /**********************************
     * DATA MEMBERS
     ********************************/
    private static final String TAG = "Create New Activity";
    Button addPhoto, publishNow;
    ImageView photo1, photo2, photo3;

    // data members used for addImages button functionality
    private static final int PICK_IMAGE = 100;
    Uri photo1URI, photo2URI, photo3URI;

    // firebase cloud storage refs
    private Uri mDownloadUrl = null;
    private StorageReference mStorageRef;

    //StorageReference storageRef = storage.getReference();
    //StorageReference mountainImagesRef = storageRef.child("filePathTo/whatPic.jpg");


    /**********************************
     * onCreate for CreateNew
     * Creates the objects necessary
     * for a new Phojo creation
     * @param savedInstanceState
     ********************************/
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new);

        // get firebase authentication
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        // hide the title bar
        try
        {
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
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.publishNow:
                // we will need to add functionality here to get the category and description
                // and the saved image Uri's.................................................
                Intent openShareRecentActivity = new Intent(this, ShareRecent.class);
                openShareRecentActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(openShareRecentActivity, 0);
                break;
            case R.id.addPhoto:
                openGallery();
                //uploadPhoto(fileUri);
                break;
            default:
                break;
        }
    }

    /***********************************
     * openGallery for addImages button
     **********************************/
    private void openGallery()
    {
        Intent gallery = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE)
        {

            if(photo1URI==null)
            {
                photo1URI = data.getData();
                photo1.setImageURI(photo1URI);
            } else if (photo2URI==null)
            {
                photo2URI = data.getData();
                photo2.setImageURI(photo2URI);
            } else {
                photo3URI = data.getData();
                photo3.setImageURI(photo3URI);
            }
        }
    }

    /***********************************
     * uploadPhoto
     * adapted from https://github.com/
     * firebase/
     **********************************/
    private void uploadPhoto(Uri fileUri) {
        Log.d(TAG, "uploadFromUri:src:" + fileUri.toString());

        // Save the File URI
        if(photo1URI != null)
        {
            Uri mFileUri = photo1URI;
        }
        if(photo2URI != null)
        {
            Uri mFileUri2 = photo2URI;
        }
        if(photo3URI != null)
        {
            Uri mFileUri3 = photo3URI;
        }

        mDownloadUrl = null;

        final StorageReference photoRef = mStorageRef.child("photos")
                .child(fileUri.getLastPathSegment());
        photoRef.putFile(fileUri).
                addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>()
                {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot)
                    {
                       Log.i(TAG, "Pushing in progress.");
                    }
                });
    }
    }