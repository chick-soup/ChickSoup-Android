package com.example.kakaotalk_dms.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class UtilClass {
    companion object{

        var pref: SharedPreferences? = null

        fun getToken(context: Context): String {
            if(pref == null) pref = context.getSharedPreferences("chick_soup",MODE_PRIVATE)
            return pref?.getString("token","").toString()
        }
        fun getToken():String?{
            return pref?.getString("token","")
        }
        fun saveToken(context: Context, token:String){
            if(pref == null) pref = context.getSharedPreferences("chick_soup", MODE_PRIVATE)
            val editor:SharedPreferences.Editor = pref!!.edit()
            editor.putString("token",token)
            editor.apply()
        }
        fun saveRefreshToken(context: Context, token: String){
            if(pref == null) pref = context.getSharedPreferences("refresh_toekn", MODE_PRIVATE)
            val editor:SharedPreferences.Editor = pref!!.edit()
            editor.putString("refreshToken",token)
            editor.apply()
        }
        fun getRefreshToken(context: Context): String {
            if(pref == null) pref = context.getSharedPreferences("refresh_token",MODE_PRIVATE)
            return pref?.getString("refreshToken","").toString()
        }
        fun clearToken(context: Context){
            if(pref == null) pref = context.getSharedPreferences("chick_soup", MODE_PRIVATE)
            val editor:SharedPreferences.Editor = pref!!.edit()
            editor.clear()
            editor.apply()

        }
    }
}