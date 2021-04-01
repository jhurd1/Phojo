package com.example.phojo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


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
public class CreateNew extends AppCompatActivity implements OnClickListener
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
    private Uri fileUri = null;
    private StorageReference mStorageRef;
    private ArrayList<String> photoPaths; // an array to store the paths to the photos on device
    private int array_position = 0;
    private FirebaseAuth mAuth;

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
        mAuth = FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        photoPaths = new ArrayList<>();
        //checkFilePermissions();

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

    /**********************************
     * loadImages
     * add file paths into the array
     * as adapted from codingWithMitch
     ********************************/
    private void loadImages() throws FileNotFoundException
    {
        String path = photoPaths.get(array_position);
    }

    /**********************************
     * addFilePaths()
     * add file paths into the array
     * as adapted from codingWithMitch
     ********************************/
    private void addFilePaths() throws FileNotFoundException {
        Log.d(TAG, "");
        String path = System.getenv("EXTERNAL_STORAGE");
        //photoPaths.add(path+"/");
        photoPaths.add(fileUri.toString());
        loadImages();
    }

    /**********************************
     * checkFilePermissions()
     ********************************/
/*    private void checkFilePermissions()
    {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.R)
        {
            int permissionCheck = UploadActivity.this.checkSelfPermission("Manifest.permission.READ_EXTERNAL_STORAGE");
            permissionCheck += UploadActivity.this.checkSelfPermission("Manifest.permission.WRITE_EXTERNAL_STORAGE");
            if (permissionCheck != 0)
            {
                this.requestPermissions(new String[]
                        {
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                        } else
                            {
                            Log.d(TAG, "No permission needed."));
            }
            }
        }
    }*/

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

                    //uploadPhoto(fileUri);
                    if(array_position > 0)
                    {
                        array_position -= 1;
                        try {
                            loadImages();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    String name = addPhoto.getText().toString(); // is addPhoto the right editText?
                    if (!name.equals(""))
                    {
                        fileUri = Uri.fromFile(new File(photoPaths.get(array_position)));
                        mStorageRef = mStorageRef.child("images/users/" + userID + "/" + name +
                                ".jpg"); //userID is the hash from auth sect. in firebase console!
                        // consider adding an onsuccess listener here
                        Toast.makeText(CreateNew.this, "Upload presumed successful, but" +
                                        "was it?.",
                                Toast.LENGTH_SHORT).show();
                        // consider adding a progressDialog in the class which is dismissed on this line
                        // then, consider adding an onfailurelistener here in case it failed
                    }
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
        String photoPath = "/Users/jamiehurd/Desktop/Images/phil_approval.png";
        Intent intent = new Intent(photoPath);
        fileUri = intent.getParcelableExtra(/*a path to a photo??*/"");
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

        fileUri = null; // needed for reset???

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