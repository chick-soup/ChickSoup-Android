package com.example.kakaotalk_dms

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitNetwork {
    @POST("/email/check")
    fun sendEmail(@Body signup:SignUp): Call<Jsonparse>

    @POST("/email/auth")
    fun sendCode(@Body signup: SignUp): Call<Jsonparse>
}