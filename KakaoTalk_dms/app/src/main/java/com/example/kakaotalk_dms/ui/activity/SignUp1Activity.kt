package com.example.kakaotalk_dms.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kakaotalk_dms.*
import kotlinx.android.synthetic.main.activity_sign_up1.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp1Activity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up1)

        signup_idInputLayout.isErrorEnabled = true
        signup_passwordInputLayout.isErrorEnabled = true
        codeTextInputLayout.isErrorEnabled = true

        signup_next_btn.setOnClickListener {
            val isEmailError:Boolean = emailError(signup_id.text.toString())
            val isPasswordEqual:Boolean = passwordCheck(signup_password.text.toString(), password_check.text.toString())

            if(isEmailError&&isPasswordEqual)
                startActivity<SignUp2Activity>()
        }
        sendEmail_btn.setOnClickListener {
            val isEmailError:Boolean = emailError(signup_id.text.toString())
            if(isEmailError)
                signup_post()
        }
        sendCode_btn.setOnClickListener {
            auth_code()
        }
    }
    fun emailError(signup_id:String): Boolean {
        if(android.util.Patterns.EMAIL_ADDRESS.matcher(signup_id).matches()){
            signup_idInputLayout.error = ""
            return true
        }
        else{
            signup_idInputLayout.error = "        이메일 형식이 맞지 않습니다."
            return false
        }
    }
    fun passwordCheck(signup_password:String, password_check: String):Boolean{
        if(signup_password.equals(password_check)){
            check_pw.error = ""
            return true
        }
        else{
            check_pw.error = "      비밀번호가 일치하지 않습니다."
            return false
        }
    }
    fun signup_post(){
        val userEmail:String = signup_id.text.toString()
        val call:Call<Jsonparse> = Retrofit().service.sendEmail(SignUp(userEmail))

        call.enqueue(object:Callback<Jsonparse>{
            override fun onFailure(call: Call<Jsonparse>, t: Throwable) {
                Log.e("fail",t.localizedMessage)
            }
            override fun onResponse(call: Call<Jsonparse>, response: Response<Jsonparse>) {
                toast("이메일 인증코드 발송 ")
                Log.d("success", response.code().toString())
            }
        })
    }

    fun auth_code() {
        val userEmail: String = signup_id.text.toString()
        val auth_code: String = check_code.text.toString()
        val call: Call<Jsonparse> = Retrofit().service.sendCode(SignUp(userEmail, auth_code))

        call.enqueue(object : Callback<Jsonparse> {
            override fun onFailure(call: Call<Jsonparse>, t: Throwable) {
                Log.e("auth_code_error", t.printStackTrace().toString())
            }

            override fun onResponse(call: Call<Jsonparse>, response: Response<Jsonparse>) {
                if (response.code() == 200) {
                    signup_id.isFocusable = false
                    signup_id.isClickable = false
                    toast("인증성공")
                }
            }
        })
    }

}
