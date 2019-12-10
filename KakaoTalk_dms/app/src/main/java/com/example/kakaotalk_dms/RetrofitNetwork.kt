package com.example.kakaotalk_dms

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface RetrofitNetwork {
    @POST("/email/check")
    fun sendEmail(@Body signup1: SignUp1): Call<SignUp1>

    @POST("/email/auth")
    fun sendCode(@Body signup1: SignUp1): Call<SignUp1>

    @POST("/signup")
    fun sendPassword(@Body signup1: SignUp1): Call<SignUp1>

    @Multipart
    @POST("/signup/profile")
    fun sendProfile(@Header("Authorization") authorization: String, @Part file: MultipartBody.Part, @Part("nickname") nick:RequestBody): Call<UploadSuccess>

    @POST("/login")
    fun sendLogin(@Body signIn: SignIn):Call<SignIn>
}
