package com.example.phojo;

import android.graphics.Color;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class BaseActivity extends AppCompatActivity {

    protected void showError(View view, String error) {
        showMessage(view, Color.RED, error);
    }

    protected void showSuccess(View view, String success){
        showMessage(view, Color.GREEN, success);
    }

    protected void showMessage(View view, int color, String message){
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", null);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(color);
        snackbar.show();
    }
}
