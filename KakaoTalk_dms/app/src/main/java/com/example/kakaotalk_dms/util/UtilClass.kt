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
    }
}