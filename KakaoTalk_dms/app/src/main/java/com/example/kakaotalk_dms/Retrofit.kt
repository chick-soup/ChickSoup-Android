package com.example.kakaotalk_dms

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://ec2-13-125-190-40.ap-northeast-2.compute.amazonaws.com:8080/")
        .addConverterFactory(
            GsonConverterFactory.create()
        ).build()
    val service = retrofit.create(RetrofitNetwork::class.java)
}