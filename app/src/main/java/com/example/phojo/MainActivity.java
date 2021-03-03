package com.example.phojo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    private final String testString = "test real string";
    private static final String FIRSTNAME = "";
    private static final String LASTNAME = "";
    private static final String EMAIL = "";
    private static final String PASSWORD = "";
    private static final String TAG = "";
    Context context = getActivity();
    Resources res = getResources();
    String[] userData = res.getStringArray(R.array.preference_file_key);
    SharedPreferences sp = context.getSharedPreferences(
            String.valueOf(res.getStringArray(R.array.preference_file_key)), Context.MODE_PRIVATE);


    public MainActivity(Context context)
    {
    }

    public MainActivity()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public String getTestString()
    {
        return testString;
    }

    public String getHelloWorldString()
    {
        return testString;
    }

    public Context getActivity()
    {
        Context c = null;
        return c;
        /*View view = null;
        return view;*/
    }

    protected void onClick(Context c) // changed to context from view
    {

        //SharedPreferences sharedPreferences = getSharedPreferences ("userData", Context.MODE_PRIVATE);
        SharedPreferences.Editor Edit=sp.edit();
        Edit.putString(FIRSTNAME, "");
        Edit.putString(LASTNAME, "");
        Edit.putString(EMAIL, "");
        Edit.putString(PASSWORD, "");
        Edit.putString(TAG, "");

    }
}
