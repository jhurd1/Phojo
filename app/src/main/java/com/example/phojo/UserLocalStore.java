package com.example.phojo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class UserLocalStore {
    private static final String USER_DATA_KEY = "USER_DATA";
    public static final String SP_NAME = "userdetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user) {
        /**
         * TODO: Serialize this data instead of storing it all individually.  See registerUser() and loginUser() methods.
         */
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("firstname", user.firstname);
        spEditor.putString("middleinitial", user.middleinitial);
        spEditor.putString("lastname", user.lastname);
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.password);
        spEditor.putString("userTag", user.userTag);
        spEditor.commit();
    }

    public User getLoggedInUser() {
        // TODO: Deserialize this data instead of pulling it all individually. See registerUser() and loginUser() methods.
        String firstname = userLocalDatabase.getString("firstname", "");
        String middleinitial = userLocalDatabase.getString("middleinitial", "");
        String lastname = userLocalDatabase.getString("lastname", "");
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");
        String userTag = userLocalDatabase.getString("userTag", "");

        User storedUser = new User(firstname, middleinitial, lastname, username, password, userTag);

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

    /**
     * Used to find a user in the sharedPreferences.
     *
     * @param activity
     * @param username
     * @param password
     * @return LoginUserResult
     */
    public static LoginUserResult loginUser(Activity activity, String username, String password){
        // Get SharedPreferences to save data to.
        SharedPreferences sharedPreferences = activity.getSharedPreferences(USER_DATA_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Lookup user by username
        String regDataJson = sharedPreferences.getString(username, null);
        if(regDataJson != null){
            // Deserialize user data back out
            User user = new Gson().fromJson(regDataJson, User.class);
            if(user.getPassword().equals(password)){
                // Valid user credentials
                LoginUserResult result = new LoginUserResult(false, "Found User Data");
                result.setUser(user);
                return result;
            }
        }
        return new LoginUserResult(true, "Invalid user, try again.");
    }

    public static OperationResult registerUser(Activity activity, User registeredData){
        // Get SharedPreferences to save data to.
        SharedPreferences sharedPreferences = activity.getSharedPreferences(USER_DATA_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Check if user is already registered.
        String username = registeredData.getUsername();
        if(username == null || username.trim().equals("")){
            return new OperationResult(true, "Username cannot be blank.");
        }

        String regDataJson = sharedPreferences.getString(username, null);
        if(regDataJson != null){
            // User already exists with that username
            // Show user an error message, to have them choose a new username
            return new OperationResult(true, "Username is already taken.  Please choose a new username.");
        } else {
            // Register data for the first time
            // Serialize data into json for storage
            regDataJson = new Gson().toJson(registeredData);
            String userPassword = registeredData.getPassword();
            editor.putString(username, regDataJson);
            editor.commit();
            return new OperationResult(false, "User was successfully registered.");
        }
    }
}

