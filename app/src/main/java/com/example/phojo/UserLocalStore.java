package com.example.phojo;

import android.content.Context;
import android.content.SharedPreferences;

public class UserLocalStore {
    public static final String SP_NAME = "userdetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("firstname", user.firstname);
        spEditor.putString("middleinitial", user.middleinitial);
        spEditor.putString("lastname", user.lastname);
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.passowrd);
        spEditor.commit();
    }

    public User getLoggedInUser() {
        String firstname = userLocalDatabase.getString("firstname", "");
        String middleinitial = userLocalDatabase.getString("middleinitial", "");
        String lastname = userLocalDatabase.getString("lastname", "");
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");

        User storedUser = new User(firstname, middleinitial, lastname, username, password);

        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn() {
        if(userLocalDatabase.getBoolean("LoggedIn", false) == true){
            return true;
        }else{
            return false;
        }
    }

    public void clearUserData() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}

