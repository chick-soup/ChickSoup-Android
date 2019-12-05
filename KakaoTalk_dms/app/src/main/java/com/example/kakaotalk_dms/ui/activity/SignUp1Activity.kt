package com.example.kakaotalk_dms.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kakaotalk_dms.R
import kotlinx.android.synthetic.main.activity_sign_up1.*
import org.jetbrains.anko.startActivity

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
            emailError(signup_id.text.toString())
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

}
