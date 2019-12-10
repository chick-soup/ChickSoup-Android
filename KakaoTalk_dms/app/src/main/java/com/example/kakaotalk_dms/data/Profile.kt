package com.example.kakaotalk_dms.data

import com.google.gson.annotations.SerializedName

class Profile {
    @SerializedName("access_token")
    lateinit var userToken:String

    @SerializedName("id")
    lateinit var imageId:String

    @SerializedName("nickname")
    lateinit var userNick:String

    @SerializedName("status_message")
    lateinit var userMessage:String
}