package com.example.kakaotalk_dms.ui.activity

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kakaotalk_dms.*
import kotlinx.android.synthetic.main.activity_signin.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.sign

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        login_btn.setOnClickListener {
            signin_post()
        }
        go_signup.setOnClickListener {
            startActivity<SignUp1Activity>()
        }

    }
    fun signin_post(){
        Log.d("come","ddd")
        val userEmail = signin_id.text.toString()
        val userPassword = signin_password.text.toString()

        val call= Retrofit().service.sendLogin(SignIn(userEmail, userPassword))

        call.enqueue(object: Callback<SignIn>{
            override fun onFailure(call: Call<SignIn>, t: Throwable) {
                Log.e("signin_fail",t.message.toString())
            }

            override fun onResponse(call: Call<SignIn>, response: Response<SignIn>) {
                Log.d("signin_success",response.code().toString())
                toast("로그인 성공")
                if(response.code() == 200) {
                    startActivity<MainActivity>()
                    val jwt: String = response.body()!!.userToken

                    val prefs = getSharedPreferences("com.example.kakaotalk_dms.ui.SignInActivity",MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = prefs.edit()
                    editor.putString("login_token",jwt)
                    editor.apply()

                    val login_jwt = prefs.getString("login_token","")
                    Log.d("login_token",login_jwt.toString())
                }
            }

        })
    }
}

