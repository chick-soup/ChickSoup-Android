package com.example.kakaotalk_dms

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface RetrofitNetwork {
    @POST("/email/check")
    fun sendEmail(@Body signup1:SignUp1): Call<SignUp1>

    @POST("/email/auth")
    fun sendCode(@Body signup1: SignUp1): Call<SignUp1>

    @POST("/user/signup")
    fun sendPassword(@Body signup1: SignUp1): Call<SignUp1>

    @POST("/user/signup/profile")
    fun sendProfile(@Header("access_token") authorization:String, @Body signUp2: SignUp2):Call<SignUp2>
    }
