package com.example.kakaotalk_dms.data

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

class SignIn(
    @SerializedName("email")
    val nickname:String,
    @SerializedName("password")
    val password:String,
    @SerializedName("minute")
    val minute:Int = 60
) {
    @SerializedName("access_token")
    lateinit var userToken:String

    @SerializedName("refresh_token")
    lateinit var userRefreshToken:String
}