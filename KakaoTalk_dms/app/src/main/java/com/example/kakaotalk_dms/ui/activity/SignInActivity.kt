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
            startActivity<MainActivity>()
        }
        go_signup.setOnClickListener {
            startActivity<SignUp1Activity>()
        }

    }
}

