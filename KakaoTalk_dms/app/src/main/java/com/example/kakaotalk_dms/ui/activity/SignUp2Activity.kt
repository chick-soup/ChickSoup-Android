package com.example.kakaotalk_dms.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.ui.fragment.HideFragment
import kotlinx.android.synthetic.main.activity_sign_up2.*
import org.jetbrains.anko.startActivity

class SignUp2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up2)

        compelete_signup_btn.setOnClickListener {
            startActivity<SignInActivity>()
        }
    }

}
