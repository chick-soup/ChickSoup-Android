package com.example.kakaotalk_dms.ui.activity

import android.graphics.Color
import android.graphics.Color.RED
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kakaotalk_dms.*
import kotlinx.android.synthetic.main.activity_sign_up1.*
import org.jetbrains.anko.backgroundColorResource
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
            val isEmailError: Boolean = emailError(signup_id.text.toString())
            val isPasswordEqual: Boolean =
                passwordCheck(signup_password.text.toString(), password_check.text.toString())


            if (isEmailError && isPasswordEqual) {
                signup_password()
                startActivity<SignUp2Activity>()
            }

        }
        sendEmail_btn.setOnClickListener {
            val isEmailError: Boolean = emailError(signup_id.text.toString())
            if (isEmailError)
                signup_post()
        }
        sendCode_btn.setOnClickListener {
            Log.d("ssddddddddddsdfsdfsdf", "aaa")
            auth_code()
        }
    }

    fun emailError(signup_id: String): Boolean {
        if(signup_id.isEmpty()){
            signup_idInputLayout.error="비밀번호를 입력하세요"
            return false
        }
        else {
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(signup_id).matches()) {
                signup_idInputLayout.error = ""
                return true
            } else {
                signup_idInputLayout.error = "        이메일 형식이 맞지 않습니다."
                return false
            }
        }
    }

    fun passwordCheck(signup_password: String, password_check: String): Boolean {
        if (signup_password.equals(password_check)) {
            check_pw.error = ""
            return true
        } else {
            check_pw.error = "      비밀번호가 일치하지 않습니다."
            return false
        }
    }

    fun isIdCorrect(auth_code: String): Boolean {
        if (auth_code.equals("200")) {
            toast("이메일 인증코드 발송 ")
            Log.d("success", auth_code.toString())
            return true
        } else if (auth_code.equals("470")) {
            toast("이미 인증이 완료된 이메일 입니다.")
            return false
        }
        else
            return false
    }

    fun signup_post() {
        val userEmail: String = signup_id.text.toString()
        val call: Call<SignUp> = Retrofit().service.sendEmail(SignUp(userEmail))

        call.enqueue(object : Callback<SignUp> {
            override fun onFailure(call: Call<SignUp>, t: Throwable) {
                Log.e("fail", t.message.toString())
            }

            override fun onResponse(call: Call<SignUp>, response: Response<SignUp>) {
                isIdCorrect(response.code().toString())
            }
        })
    }

    fun auth_code() {
        val userEmail: String = signup_id.text.toString()
        val auth_code: String = check_code.text.toString()
        val call: Call<SignUp> = Retrofit().service.sendCode(SignUp(userEmail, auth_code, ""))

        call.enqueue(object : Callback<SignUp> {
            override fun onFailure(call: Call<SignUp>, t: Throwable) {
                Log.e("auth_code_error", t.printStackTrace().toString())
            }

            override fun onResponse(call: Call<SignUp>, response: Response<SignUp>) {
                Log.d("auth_response", "response")
                if (response.code() == 200) {
                    signup_id.isFocusable = false
                    signup_id.isClickable = false
                    signup_id.setBackgroundResource(R.color.gray)
                    check_code.isFocusable = false
                    check_code.isClickable = false
                    check_code.setBackgroundResource(R.color.gray)
                    toast("이메일 인증성공")
                    Log.d("auth_code", response.code().toString())
                } else if (response.code() == 470) toast("이미 인증 처리가 완료된 이메일 입니다.")
                else if (response.code() == 471) toast("이메일 인증이 완료되지 않았습니다.")
                else if (response.code() == 472) toast("이메일의 인증코드가 맞지 올바르지 않습니다.")
            }
        })
    }

    fun signup_password() {
        val userEmail = signup_id.text.toString()
        val userPassword = signup_password.text.toString()

        val call: Call<SignUp> =
            Retrofit().service.sendPassword(SignUp(userEmail, "", userPassword))

        call.enqueue(object : Callback<SignUp> {
            override fun onFailure(call: Call<SignUp>, t: Throwable) {
                Log.e("signup_password", t.message.toString())
            }

            override fun onResponse(call: Call<SignUp>, response: Response<SignUp>) {
                Log.d("signup_password", response.code().toString())
            }
        })
    }
}
