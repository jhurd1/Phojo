package com.example.phojo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateNew extends AppCompatActivity implements View.OnClickListener {

    Button addDescription, addImages, selectCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new);

        addDescription = (Button)findViewById(R.id.addDescription);
        addImages = (Button)findViewById(R.id.addImages);
        selectCategory = (Button)findViewById(R.id.selectCategory);
    }

    @Override
    public void onClick(View v) {

    }
}