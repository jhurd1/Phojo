package com.example.phojo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    public static final String TAG = "checkTitleBar";
    Button addDescription, addImages, selectCategory, publish;

    /**********************************
     * onCreate for CreateNew
     * Creates the objects necessary
     * for a new Phojo creation
     * @param savedInstanceState
     ********************************/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // hide the title bar
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e)
        {
            Log.d(TAG,"hide title bar failed for createNew.java");
        }
        setContentView(R.layout.activity_create_new);

        addDescription = (Button)findViewById(R.id.addDescription);
        addImages = (Button)findViewById(R.id.addImages);
        selectCategory = (Button)findViewById(R.id.selectCategory);
        publish = (Button)findViewById(R.id.publish);
    }

    /********************************
     * onClick for CreateNew's
     * Phojo data upload options
     * @param v
     *******************************/
    @Override
    public void onClick(View v) {

    }
}