package com.example.kakaotalk_dms.data

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

class SignIn(
    @SerializedName("email")
    val nickname:String,
    @SerializedName("password")
    val password:String
) {
    @SerializedName("access_token")
    lateinit var userToken:String
}