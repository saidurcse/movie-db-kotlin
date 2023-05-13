package com.example.saidur.utils;

import static android.content.Context.MODE_PRIVATE;
import static com.example.saidur.utils.Config.FIRST_STATUS;

import android.content.Context;
import android.content.SharedPreferences;

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
        editor.apply();
    }

    public void setFirstTimeUser(boolean status){
        editor.putBoolean(FIRST_STATUS, status);
        editor.apply();
    }

    public boolean getFirstTimeUser(){
        return sharedPreferences.getBoolean(FIRST_STATUS, false);
    }

    public static void firstTimeAskingPermission(Context context, String permission, boolean isFirstTime){
        SharedPreferences sharedPreference = context.getSharedPreferences(Config.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        sharedPreference.edit().putBoolean(permission, isFirstTime).apply();
    }

    public static boolean isFirstTimeAskingPermission(Context context, String permission){
        return context.getSharedPreferences(Config.SHARED_PREFERENCES_NAME, MODE_PRIVATE).getBoolean(permission, true);
    }

}