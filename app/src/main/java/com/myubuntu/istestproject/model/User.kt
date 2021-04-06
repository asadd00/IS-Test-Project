package com.myubuntu.istestproject.model

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson

class User {
    var token = ""
    var hasError = false
    var message = ""

    companion object {

        private val SP_KEY = "user"

        fun saveUser(user: User, context: Context) {
            val pref = context.getSharedPreferences("user", MODE_PRIVATE)
            val sUser = Gson().toJson(user)
            pref.edit().putString(SP_KEY, sUser).apply()
        }

        fun getUser(context: Context): User? {
            val pref = context.getSharedPreferences("user", MODE_PRIVATE)
            val sUser = pref.getString(SP_KEY, "")
            return if (sUser == "") null else Gson().fromJson<User>(sUser, User::class.java)
        }

        fun removeUser(context: Context) {
            val pref = context.getSharedPreferences("user", MODE_PRIVATE)
            pref.edit().remove(SP_KEY).apply()
        }
    }
}