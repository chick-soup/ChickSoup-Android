package com.example.kakaotalk_dms

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    val retrofit = Retrofit.Builder().baseUrl("http://chicksoup.pythonanywhere.com")
                            .addConverterFactory(GsonConverterFactory.create()).build()
    val service = retrofit.create(RetrofitNetwork::class.java)
}