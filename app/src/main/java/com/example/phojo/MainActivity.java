package com.example.phojo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final String testString = "test real string";

    public MainActivity(Context context) {
    }

    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public String getTestString() {
        return testString;
    }

    public String getHelloWorldString() {
        return testString;
    }

    public View getActivity() {
        View view = null;
        return view;
    }
}