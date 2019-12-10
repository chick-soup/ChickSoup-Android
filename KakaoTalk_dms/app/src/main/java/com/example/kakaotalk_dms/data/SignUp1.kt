package com.example.kakaotalk_dms.data

import com.google.gson.annotations.SerializedName

class SignUp1(
    @SerializedName("email")
    val userEmail:String,
    @SerializedName("auth_code")
    val auth_code:String,
    @SerializedName("password")
    val userPassword:String
){
    @SerializedName("access_token")
    lateinit var userToken:String

    constructor(userEmail: String) : this(userEmail,"","")
    constructor(userEmail: String, userPassword:String):this(userEmail,"",userPassword)
}
