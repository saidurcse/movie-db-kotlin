package com.example.saidur.utils;

import static android.content.Context.MODE_PRIVATE;
import static com.bayit.awareness.util.Config.FEEDBACK_STATUS;
import static com.bayit.awareness.util.Config.FIRST_STATUS;
import static com.bayit.awareness.util.Config.INTRO_STATUS;
import static com.bayit.awareness.util.Config.PHONE_NO;

import android.content.Context;
import android.content.SharedPreferences;

import com.bayit.awareness.util.Config;

public class MyPreferences {

    private static MyPreferences myPreferences;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private MyPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(Config.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public static MyPreferences getPreferences(Context context) {
        if (myPreferences == null) myPreferences = new MyPreferences(context);
        return myPreferences;
    }

    public void clearAfterPressSignout() {
        editor.remove(FIRST_STATUS);
        editor.remove(PHONE_NO);
        editor.apply();
    }

    public void setPhone(String phone){
        editor.putString(PHONE_NO, phone);
        editor.apply();
    }

    public String getPhone(){
        return sharedPreferences.getString(PHONE_NO, "");
    }

    public void setFeedback(String feedback){
        editor.putString(FEEDBACK_STATUS, feedback);
        editor.apply();
    }

    public String getFeedback(){
        return sharedPreferences.getString(FEEDBACK_STATUS, "");
    }

    public void setFirstTimeUser(boolean status){
        editor.putBoolean(FIRST_STATUS, status);
        editor.apply();
    }

    public boolean getFirstTimeUser(){
        return sharedPreferences.getBoolean(FIRST_STATUS, false);
    }

    public void setIntroStatus(boolean status){
        editor.putBoolean(INTRO_STATUS, status);
        editor.apply();
    }

    public boolean getIntroStatus(){
        return sharedPreferences.getBoolean(INTRO_STATUS, false);
    }

    public static void firstTimeAskingPermission(Context context, String permission, boolean isFirstTime){
        SharedPreferences sharedPreference = context.getSharedPreferences(Config.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        sharedPreference.edit().putBoolean(permission, isFirstTime).apply();
    }

    public static boolean isFirstTimeAskingPermission(Context context, String permission){
        return context.getSharedPreferences(Config.SHARED_PREFERENCES_NAME, MODE_PRIVATE).getBoolean(permission, true);
    }

}