package com.example.saidur.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {
    private val sharedPreferences: SharedPreferences
    private val beaconPref = "weatherdb-prefs"
    private val editor: SharedPreferences.Editor

    init {
        sharedPreferences = context.getSharedPreferences(beaconPref, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.apply()
    }

    fun put(key: String, value: String?) {
        editor.putString(key, value).apply()
    }

    operator fun get(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue)!!
    }

    fun put(key: String?, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    operator fun get(key: String?, defaultValue: Boolean): Boolean? {
        return sharedPreferences.getBoolean(key, defaultValue)
    }
}
