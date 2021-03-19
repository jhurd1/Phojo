package com.example.phojo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * USERLOCALSTORE CLASS
 * A class for managing user
 * login, user data storage,
 * and clearing of memory
 * occupied by user data.
 *
 * @author chrisfolwer,
 * @author edgarcobian
 * @author jamiehurd
 * @author danallewellyn
 */
public class UserLocalStore {

    /******************************
     * DATA MEMBERS
     *****************************/
    public static final String SP_NAME = "userdetails";
    public static final String TAG = "debugCommit";
    public static final String TAG2 = "userObjectInstanced";
    SharedPreferences userLocalDatabase;

    /******************************
     * UserLocalStore
     * A function for getting
     * shared preferences.
     * @param context
     *****************************/
    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    /******************************
     * storeUserData
     * A function for inputting
     * user data into memory.
     * @param user
     *****************************/
    public void storeUserData(User user) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("firstname", user.getFirstname());
        spEditor.putString("middleinitial", user.getMiddleinitial());
        spEditor.putString("lastname", user.getLastname());
        spEditor.putString("username", user.getEmail());
        spEditor.putString("password", user.getPassword());
        spEditor.putString("userTag", user.getUserTag());
        spEditor.commit();
    }

    /******************************
     * ACCESSORS
     *****************************/

    /******************************
     * getLoggedInUser()
     * A function to access
     * the currently logged in user's
     * information.
     *****************************/
    public User getLoggedInUser() {
        String firstname = userLocalDatabase.getString("firstname", "");
        String middleinitial = userLocalDatabase.getString("middleinitial", "");
        String lastname = userLocalDatabase.getString("lastname", "");
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");
        String userTag = userLocalDatabase.getString("userTag", "");

        User storedUser = new User(firstname, middleinitial, lastname, username, password, userTag);
        Log.i(TAG2, "User object instanced from UserLocalStore.java");
        return storedUser;
    }

    /******************************
     * getUserLoggedIn
     * if user logged in
     * return true
     *****************************/
    public boolean getUserLoggedIn() {
        if(userLocalDatabase.getBoolean("LoggedIn", false) == true){
            return true;
        }else{
            return false;
        }
    }

    /******************************
     *  MUTATORS
     *****************************/

    /******************************
     * setUserLoggedIn
     * sets the logged in user
     * @param loggedIn
     *****************************/
    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
        Log.d(TAG, "Commit of login status called.");
    }

    /******************************
     * OTHER MEMBERS
     *****************************/

    /******************************
     * clearUserData
     * clears the user data
     * held in sharedPreferences
     * memory.
     *****************************/
    public void clearUserData() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
        Log.d(TAG, "User data clear commited.");
    }
}

