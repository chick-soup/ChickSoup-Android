package com.example.kakaotalk_dms.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kakaotalk_dms.R
import kotlinx.android.synthetic.main.activity_signin.*
import org.jetbrains.anko.startActivity
import kotlin.math.sign

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        signin_idInputLayout.isErrorEnabled = true

        login_btn.setOnClickListener {
            signin_email_check(signin_id.text.toString())
        }
        go_signup.setOnClickListener {
            startActivity<SignUp1Activity>()
        }

    }

    fun signin_email_check(signin_id: String): Boolean {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(signin_id).matches()) {
            signin_idInputLayout.error = ""
            Log.d("success Login", "god")
            startActivity<MainActivity>()
            return true
        } else {
            signin_idInputLayout.error = "       이메일 형식이  맞지 않습니다"
            Log.d("fail","failure")
            return false
        }
    }
}

