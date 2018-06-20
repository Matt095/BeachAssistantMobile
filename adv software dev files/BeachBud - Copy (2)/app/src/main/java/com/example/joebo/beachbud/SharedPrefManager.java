package com.example.joebo.beachbud;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SharedPrefManager {                    //shared preference manager to store info on phone regarding users log in status

        private static SharedPrefManager mInstance;
        private static Context mCtx;


    private static final String SHARED_PREF_NAME = "mysharedpref12";        //shared preferences to store
    private static final String KEY_USERNAME = "username";
    private static final String KEY_USER_EMAIL = "useremail";
    private static final String KEY_USER_ID = "userid";
    private static final String KEY_USER_FULL_NAME = "fullName";
    private static final String KEY_USER_AGE = "age";
    private static final String KEY_USER_GENDER = "gender";


        private SharedPrefManager(Context context) {
            mCtx = context;
            }

        public static synchronized SharedPrefManager getInstance(Context context) {     //if there is no saved data then to create a new instance
            if (mInstance == null) {
                mInstance = new SharedPrefManager(context);
            }
            return mInstance;
        }

        public boolean userLogin(int id,  String username, String email, String fullName, String age, String gender){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_USER_ID, id);
        editor.putString(KEY_USER_EMAIL, email);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_USER_FULL_NAME, fullName);
        editor.putString(KEY_USER_AGE, age);
        editor.putString(KEY_USER_GENDER, gender);              //user log in data to be saved to shared preferences

        editor.apply();

        return true;
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USERNAME, null) != null){
            return true;
        }                                   //to check if user logged in
        return false;
    }
    public boolean logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();             //once logging out the code deleted the shared prefernce so user does not remain logged in
        return true;
    }

    public String getUsername(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null);// get username and email of shared preferences
        }
    public String getUserEmail(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_EMAIL, null);
    }
}
