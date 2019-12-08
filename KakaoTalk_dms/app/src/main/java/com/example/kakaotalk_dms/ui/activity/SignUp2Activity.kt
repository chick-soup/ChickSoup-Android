package com.example.kakaotalk_dms.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.ui.fragment.HideFragment
import kotlinx.android.synthetic.main.activity_sign_up2.*
import kotlinx.android.synthetic.main.fragment_change_profile.*
import org.jetbrains.anko.startActivity
import java.io.File

class SignUp2Activity : AppCompatActivity() {

    val GET_FIRST_IMAGE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up2)

//        compelete_signup_btn.setOnClickListener {
//            startActivity<SignInActivity>()
//        }
        first_profile_image.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            startActivityForResult(intent, GET_FIRST_IMAGE)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GET_FIRST_IMAGE && resultCode == RESULT_OK && data != null && data.data != null) {
            val profileImageUri: Uri = data.data!!
            first_profile_image.setImageURI(profileImageUri)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }



}
