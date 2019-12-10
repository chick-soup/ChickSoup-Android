package com.example.kakaotalk_dms.data

import com.google.gson.annotations.SerializedName

class KakaoId{
    @SerializedName("access_token")
    lateinit var userToken:String

    @SerializedName("kakao_id")
    lateinit var userKakaoId: String
}