package com.example.phojo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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
        FirebaseStorage storage = FirebaseStorage.getInstance();
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

    /**********************************
     * includesForCreateReference()
     * as adapted from
     * github.com/firebase example
     ********************************/
    public void includesForCreateReference()
    {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        // ## Create a Reference

        // [START create_storage_reference]
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference();
        // [END create_storage_reference]

        // [START create_child_reference]
        // Create a child reference
        // imagesRef now points to "images"
        StorageReference imagesRef = storageRef.child("images");

        // Child references can also take paths
        // spaceRef now points to "images/space.jpg
        // imagesRef still points to "images"
        StorageReference spaceRef = storageRef.child(gallery.toString());
        // [END create_child_reference]

        // ## Navigate with References

        // [START navigate_references]
        // getParent allows us to move our reference to a parent node
        // imagesRef now points to 'images'
        imagesRef = spaceRef.getParent();

        // getRoot allows us to move all the way back to the top of our bucket
        // rootRef now points to the root
        StorageReference rootRef = spaceRef.getRoot();
        // [END navigate_references]

        // [START chain_navigation]
        // References can be chained together multiple times
        // earthRef points to 'images/earth.jpg'
        StorageReference earthRef = spaceRef.getParent().child(gallery.toString());

        // nullRef is null, since the parent of root is null
        StorageReference nullRef = spaceRef.getRoot().getParent();
        // [END chain_navigation]

        // ## Reference Properties

        // [START reference_properties]
        // Reference's path is: "images/space.jpg"
        // This is analogous to a file path on disk
        spaceRef.getPath();

        // Reference's name is the last segment of the full path: "space.jpg"
        // This is analogous to the file name
        spaceRef.getName();

        // Reference's bucket is the name of the storage bucket that the files are stored in
        spaceRef.getBucket();
        // [END reference_properties]

        // ## Full Example

        // [START reference_full_example]
        // Points to the root reference
        storageRef = storage.getReference();

        // Points to "images"
        imagesRef = storageRef.child(gallery.toString());

        // Points to "images/space.jpg"
        // Note that you can use variables to create child values
        String fileName = gallery.toString();
        spaceRef = imagesRef.child(fileName);

        // File path is "images/space.jpg"
        String path = spaceRef.getPath();

        // File name is "space.jpg"
        String name = spaceRef.getName();

        // Points to "images"
        imagesRef = spaceRef.getParent();
        // [END reference_full_example]
    }

    /**********************************
     * includesForUploadFiles()
     * as adapted from
     * github.com/firebase example
     ********************************/
    public void includesForUploadFiles() throws FileNotFoundException
    {
        UploadTask uploadTask1 = uploadImage(R.id.photo1, "photo1");
        UploadTask uploadTask2 = uploadImage(R.id.photo2, "photo2");
        UploadTask uploadTask3 = uploadImage(R.id.photo3, "photo3");
        // [END upload_create_reference]


//        ImageView imageView = (ImageView)findViewById(R.id.photo1);
//
//        // [START upload_memory]
//        // Get the data from an ImageView as bytes
//        imageView.setDrawingCacheEnabled(true);
//        imageView.buildDrawingCache();
//        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] data = baos.toByteArray();

//        UploadTask uploadTask = mountainsRef.putBytes(data);
//        uploadTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                // Handle unsuccessful uploads
//            }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
//                // ...
//            }
//        });
//        // [END upload_memory]
//
//        // [START upload_stream] HOW TO TAP CREATENEW'S GALLERY????
//        InputStream stream = new FileInputStream(new File(gallery.toString()));
//
//        uploadTask = mountainsRef.putStream(stream);
//        uploadTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                // Handle unsuccessful uploads
//            }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
//                // ...
//            }
//        });
//        // [END upload_stream]
//
//        // [START upload_file] HOW TO TAP CREATENEW'S GALLERY????
//        Uri file = Uri.fromFile(new File(gallery.toString()));
//        StorageReference riversRef = storageRef.child("images/"+file.getLastPathSegment());
//        uploadTask = riversRef.putFile(file);
//
//        // Register observers to listen for when the download is done or if it fails
//        uploadTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                // Handle unsuccessful uploads
//            }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
//                // ...
//            }
//        });
//        // [END upload_file]
//
//        // [START upload_with_metadata]
//        // Create file metadata including the content type
//        StorageMetadata metadata = new StorageMetadata.Builder()
//                .setContentType("image/jpg")
//                .build();
//
//        // Upload the file and metadata  HOW TO TAP CREATENEW'S GALLERY????
//        uploadTask = storageRef.child(gallery.toString()).putFile(file, metadata);
//        // [END upload_with_metadata]
//
//        // [START manage_uploads]   HOW TO TAP CREATENEW'S GALLERY????
//        uploadTask = storageRef.child(gallery.toString()).putFile(file);
//
//        // Pause the upload
//        uploadTask.pause();
//
//        // Resume the upload
//        uploadTask.resume();
//
//        // Cancel the upload
//        uploadTask.cancel();
//        // [END manage_uploads]
//
//        // [START monitor_upload_progress]
//        // Observe state change events such as progress, pause, and resume
//        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                Log.d(TAG, "Upload is " + progress + "% done");
//            }
//        }).addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
//                Log.d(TAG, "Upload is paused");
//            }
//        });
//        // [END monitor_upload_progress]
//
//        // [START upload_complete_example]
//        // File or Blob     HOW TO TAP CREATENEW'S GALLERY????
//        file = Uri.fromFile(new File(gallery.toString()));
//
//        // Create the file metadata
//        metadata = new StorageMetadata.Builder()
//                .setContentType(gallery.toString())  //HOW TO TAP CREATENEW'S GALLERY????
//                .build();
//
//        // Upload file and metadata to the path 'images/mountains.jpg'
//        uploadTask = storageRef.child("images/"+file.getLastPathSegment()).putFile(file, metadata);
//
//        // Listen for state changes, errors, and completion of the upload.
//        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                Log.d(TAG, "Upload is " + progress + "% done");
//            }
//        }).addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
//                Log.d(TAG, "Upload is paused");
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                // Handle unsuccessful uploads
//            }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                // Handle successful uploads on complete
//                // ...
//            }
//        });
//        // [END upload_complete_example]
//
//        // [START upload_get_download_url]   HOW TO TAP CREATENEW'S GALLERY????
//        final StorageReference ref = storageRef.child(gallery.toString());
//        uploadTask = ref.putFile(file);
//
//        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
//            @Override
//            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
//                if (!task.isSuccessful()) {
//                    throw task.getException();
//                }
//
//                // Continue with the task to get the download URL
//                return ref.getDownloadUrl();
//            }
//        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//            @Override
//            public void onComplete(@NonNull Task<Uri> task) {
//                if (task.isSuccessful()) {
//                    Uri downloadUri = task.getResult();
//                } else {
//                    // Handle failures
//                    // ...
//                }
//            }
//        });
        // [END upload_get_download_url]
    }

    private UploadTask uploadImage(int photoId, String photoName){
//        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        FirebaseStorage storage = FirebaseStorage.getInstance();

        // [START upload_create_reference]
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference();

        // Create a reference to 'images/mountains.jpg'
        StorageReference storageReference = storageRef.child("images/" + photoName); // how to tap the gallery here?

        ImageView imageView = (ImageView)findViewById(photoId);

        // [START upload_memory]
        // Get the data from an ImageView as bytes
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        return storageReference.putBytes(data);
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
                try {
                    includesForUploadFiles();
                    includesForCreateReference();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
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
    private void openGallery() {
//        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//        startActivityForResult(gallery, PICK_IMAGE);
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
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