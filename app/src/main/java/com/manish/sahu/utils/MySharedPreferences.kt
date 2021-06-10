package com.manish.sahu.utils

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context: Context) {
    private var sharedPreferences: SharedPreferences = context.getSharedPreferences(MYPRIF, Context.MODE_PRIVATE)
    private val context: Context = context
    private val editor: SharedPreferences.Editor? = null

    fun getMySharedPreferencesData(key: String?): String? {
        return sharedPreferences.getString(key, "")
    }

    fun setMySharedPreferencesData(key: String?, `val`: String?): Boolean {
        return sharedPreferences.edit().putString(key, `val`).commit()
    }

    fun clearMyPref(): Boolean {
        return sharedPreferences.edit().clear().commit()
    }

    companion object {
        private const val MYPRIF = "user_prif"
        const val IS_LOGIN = "is_login"
    }
}

