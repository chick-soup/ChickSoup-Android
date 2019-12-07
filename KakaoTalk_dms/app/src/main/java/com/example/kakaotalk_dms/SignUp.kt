package com.example.kakaotalk_dms

import com.google.gson.annotations.SerializedName

class SignUp(
    @SerializedName("email")
    val userEmail:String,
    @SerializedName("auth_code")
    val auth_code:String,
    @SerializedName("password")
    val userPassword:String
){
    constructor(userEmail: String) : this(userEmail,"","")
    constructor(userEmail: String, userPassword:String):this(userEmail,"",userPassword)
}
