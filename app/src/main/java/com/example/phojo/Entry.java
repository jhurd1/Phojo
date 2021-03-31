package com.example.phojo;

import android.net.Uri;

import java.util.ArrayList;

/**
 * ENTRY
 * A class representing a
 * single phojo entry
 *
 * @author edgarcobian
 * @author chrisfowler
 * @author jamiehurd
 */
public class Entry {

    /**********************************
     * DATA MEMBERS
     ********************************/
    private static final String TAG = "Phojo Entry Activity";
    String category;
    String description;
    ArrayList<Uri> uris;
    // location ?

    public Entry () {
        // default constructor
    }

    public void doSomething() {
        // placeholder
    }
}