package com.example.kakaotalk_dms

import com.example.kakaotalk_dms.data.KakaoId
import com.example.kakaotalk_dms.data.Profile
import com.example.kakaotalk_dms.data.SignIn
import com.example.kakaotalk_dms.data.SignUp1
import com.google.gson.JsonArray
import com.google.gson.JsonObject
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
    fun sendFirstProfile(@Header("Authorization") authorization: String,
                         @Part file: MultipartBody.Part ,
                         @Part("nickname") nick:RequestBody): Call<UploadSuccess>

    @POST("/login")
    fun sendLogin(@Body signIn: SignIn):Call<SignIn>

    @GET("/users/my/kakao-id")
    fun getKakaoId(@Header("Authorization") authorization: String) :Call<KakaoId>

    @GET("/users/my/profile")
    fun getProfile(@Header("Authorization") authorization: String,
                   @Query("id") id:String,
                   @Query("nickname") nick:String,
                   @Query("status_message") message:String): Call<Profile>

    @GET("/refresh")
    fun getRefreshToken(@Header("Authorization") authorization: String):Call<SignIn>

    @Multipart
    @PUT("/users/my/profile")
    fun changeProfile(@Header("Authorization") authorization: String,
                      @Part("nickname") nick:RequestBody,
                      @Part("status_message")message: RequestBody,
                      @Part("where")mobile:RequestBody): Call<Void>

    @GET("/users/my/friends")
    fun getFriends(@Header("Authorization") authorization: String): Call<JsonObject>
}
