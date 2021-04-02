package com.example.phojo;

import android.net.Uri;

import java.util.ArrayList;

/**
 * PHOJOENTRY
 * A class representing a
 * single phojo entry
 *
 * @author edgarcobian
 * @author chrisfowler
 * @author jamiehurd
 */
public class PhojoEntry {

    /**********************************
     * DATA MEMBERS
     ********************************/
    private static final String TAG = "Phojo Entry Activity";
    String category;
    String description;
    ArrayList<Uri> uris;
    // location ?

    public PhojoEntry () {
        // default constructor
    }

    public PhojoEntry(String c, String d, ArrayList<Uri> u) {
        this.category = c;
        this.description = d;
        this.uris = u;
    }

    public String getCategory() { return this.category; }

    public void setCategory(String c) { this.category = c; }

    public String getDescription() { return this.description; }

    public void setDescription(String d) { this.description = d; }

    public ArrayList<Uri> getUris() { return this.uris; }

    public void setUris(ArrayList<Uri> uriArrayList) { this.uris = uriArrayList; }
}