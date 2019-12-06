package com.example.kakaotalk_dms

import com.google.gson.annotations.SerializedName

class Jsonparse(
    @SerializedName("email")
    val userEmail: String,

    @SerializedName("auth_code")
    val auth_code: String

)