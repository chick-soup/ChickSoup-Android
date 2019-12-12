package com.example.kakaotalk_dms.ui.activity

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.Retrofit
import com.example.kakaotalk_dms.data.SignUp1
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
                signUpPost()
        }
        sendCode_btn.setOnClickListener {
            auth_code()
        }
    }

    private fun emailError(signup_id: String): Boolean {
        return if (signup_id.isEmpty()) {
            signup_idInputLayout.error = "      이메일을 입력하세요"
            false
        } else {
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(signup_id).matches()) {
                signup_idInputLayout.error = ""
                true
            } else {
                signup_idInputLayout.error = "      이메일 형식이 맞지 않습니다."
                false
            }
        }
    }

    private fun passwordCheck(signup_password: String, password_check: String): Boolean {
        return if (signup_password.isEmpty()) {
            signup_passwordInputLayout.error = "      비밀번호를 입력하세요"
            false
        } else {
            if (signup_password == password_check) {
                check_pw.error = ""
                true
            } else {
                check_pw.error = "      비밀번호가 일치하지 않습니다."
                false
            }
        }
    }

    fun isIdCorrect(auth_code: String): Boolean {
        return when (auth_code) {
            "200" -> {
                toast("이메일 인증코드 발송 ")
                Log.d("success", auth_code)
                true
            }
            "470" -> {
                toast("이미 인증이 완료된 이메일 입니다.")
                false
            }
            else -> false
        }
    }

    private fun signUpPost() {
        Log.d("signUpPost","실행")
        val userEmail: String = signup_id.text.toString()
        val call: Call<SignUp1> = Retrofit().service.sendEmail(
            SignUp1(
                userEmail
            )
        )

        call.enqueue(object : Callback<SignUp1> {
            override fun onFailure(call: Call<SignUp1>, t: Throwable) {
                Log.e("fail", t.message.toString())
            }
            override fun onResponse(call: Call<SignUp1>, response: Response<SignUp1>) {
                isIdCorrect(response.code().toString())
                Log.d("onResponse",response.code().toString())
            }
        })
    }

    private fun auth_code() {
        val userEmail: String = signup_id.text.toString()
        val authCode: String = check_code.text.toString()
        val call: Call<SignUp1> = Retrofit().service.sendCode(
            SignUp1(
                userEmail,
                authCode,
                ""
            )
        )

        call.enqueue(object : Callback<SignUp1> {
            override fun onFailure(call: Call<SignUp1>, t: Throwable) {
                Log.e("auth_code_error", t.printStackTrace().toString())
            }

            override fun onResponse(call: Call<SignUp1>, response: Response<SignUp1>) {
                Log.d("auth_response", "response")
                when {
                    response.code() == 200 -> {
                        signup_id.isFocusable = false
                        signup_id.isClickable = false
                        signup_id.setBackgroundResource(R.color.gray)
                        check_code.isFocusable = false
                        check_code.isClickable = false
                        check_code.setBackgroundResource(R.color.gray)
                        toast("이메일 인증성공")
                        Log.d("auth_code", response.code().toString())
                    }
                    response.code() == 470 -> toast("이미 인증 처리가 완료된 이메일 입니다.")
                    response.code() == 471 -> toast("이메일 인증이 완료되지 않았습니다.")
                    response.code() == 472 -> toast("이메일의 인증코드가 맞지 올바르지 않습니다.")
                }
            }
        })
    }

    private fun signup_password() {
        val userEmail = signup_id.text.toString()
        val userPassword = signup_password.text.toString()

        val call: Call<SignUp1> =
            Retrofit().service.sendPassword(
                SignUp1(
                    userEmail,
                    userPassword
                )
            )

        call.enqueue(object : Callback<SignUp1> {
            override fun onFailure(call: Call<SignUp1>, t: Throwable) {
                Log.e("signup_password", t.message.toString())
            }

            override fun onResponse(call: Call<SignUp1>, response: Response<SignUp1>) {
                Log.d("signup_password", response.code().toString())
                when {
                    response.code() == 200 -> {
                        val jwt: String = response.body()!!.userToken

                        val prefs = getSharedPreferences("com.example.kakaotalk_dms.ui.SignUp1Activity",MODE_PRIVATE)
                        val editor:SharedPreferences.Editor = prefs.edit()
                        editor.putString("signUp_token",jwt)
                        editor.apply()

                        val signUpJwt = prefs.getString("signUp_token","")
                        Log.d("jwt",signUpJwt.toString())

                    }
                    response.code() == 471 -> toast("이메일 인증이 완료되지 않았습니다.")
                    response.code() == 472 -> toast("다른 사용자가 인증한 이메일입니다.")
                }
            }
        })
    }
}
