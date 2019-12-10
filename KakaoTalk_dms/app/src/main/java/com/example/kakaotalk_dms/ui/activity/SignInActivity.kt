package com.example.kakaotalk_dms.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.Retrofit
import com.example.kakaotalk_dms.data.SignIn
import com.example.kakaotalk_dms.util.UtilClass
import kotlinx.android.synthetic.main.activity_signin.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val userEmail = signin_id.text.toString()
        val userPassword = signin_password.text.toString()

        val call= Retrofit().service.sendLogin(
            SignIn(
                userEmail,
                userPassword
            )
        )

        call.enqueue(object: Callback<SignIn>{
            override fun onFailure(call: Call<SignIn>, t: Throwable) {
                Log.e("signin_fail",t.message.toString())
            }

            override fun onResponse(call: Call<SignIn>, response: Response<SignIn>) {
                Log.d("signin_success",response.code().toString())

                if(response.code() == 200) {
                    toast("로그인 성공")
                    startActivity<MainActivity>()
                    val jwt: String = response.body()!!.userToken
                    UtilClass.saveToken(applicationContext,jwt)

                }
            }

        })
    }
}

