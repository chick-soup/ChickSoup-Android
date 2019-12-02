package com.example.kakaotalk_dms.ui.fragment

import android.app.Activity.MODE_PRIVATE
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_PICK
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toFile

import com.example.kakaotalk_dms.R
import kotlinx.android.synthetic.main.fragment_change_profile.*
import java.io.File
import java.io.FileOutputStream
import kotlin.math.log

class ChangeProfileFragment : Fragment() {

    private val GET_GALLERY_IMAGE: Int = 200
    private lateinit var mPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_change_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        change_profile_back.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
                ?.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            transaction?.replace(R.id.main_frame, SettingFragment())
            transaction?.commit()
        }
        change_image.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            startActivityForResult(intent, GET_GALLERY_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.data != null) {
           val selectedImageUri: Uri = data.data!!
          change_image.setImageURI(selectedImageUri)

//            mPreferences = activity!!.getSharedPreferences("com.example.kakaotalk_dms.ui.fragment", MODE_PRIVATE)
//            val preferencesEditor:SharedPreferences.Editor = mPreferences.edit()
//            preferencesEditor.putString("profile_image", selectedImageUri.toString())
//            preferencesEditor.apply()
        }

    }
}


