package com.example.kakaotalk_dms.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            Thread.sleep(3000)
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        } catch (e: Exception) {
            return
        }


    }
}