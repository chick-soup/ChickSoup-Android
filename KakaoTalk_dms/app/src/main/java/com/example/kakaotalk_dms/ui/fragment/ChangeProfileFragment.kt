package com.example.kakaotalk_dms.ui.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import com.example.kakaotalk_dms.R
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.android.synthetic.main.fragment_change_profile.*

class ChangeProfileFragment : Fragment() {

    private val GET_GALLERY_IMAGE: Int = 200
    private val GET_BACK_IMAGE: Int = 1
    //private lateinit var mPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_change_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        change_profile_back_btn.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
                ?.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            transaction?.replace(R.id.main_frame, AccountFragment())
            transaction?.commit()
        }
        change_profileimage_btn.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            startActivityForResult(intent, GET_GALLERY_IMAGE)
        }
        change_backimage_btn.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            startActivityForResult(intent, GET_BACK_IMAGE)
        }
        change_profile_check_btn.setOnClickListener {
            val args: Bundle = Bundle()
            args.putString("nick", change_nick_editText.text.toString())
            args.putString("state_message", change_message_editText.text.toString())

            val fragment: Fragment = AccountFragment()
            val fm: FragmentManager? = fragmentManager
            val transaction:FragmentTransaction = fm!!.beginTransaction()
            fragment.arguments = args
            transaction.replace(R.id.main_frame,fragment).addToBackStack(null).setCustomAnimations(R.anim.fade_in,R.anim.fade_out).commit()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.data != null) {
            val profileImageUri: Uri = data.data!!
            change_image.setImageURI(profileImageUri)

//            mPreferences = activity!!.getSharedPreferences("com.example.kakaotalk_dms.ui.fragment", MODE_PRIVATE)
//            val preferencesEditor:SharedPreferences.Editor = mPreferences.edit()
//            preferencesEditor.putString("profile_image", selectedImageUri.toString())
//            preferencesEditor.apply()
        } else if (requestCode == GET_BACK_IMAGE && resultCode == RESULT_OK && data != null && data.data != null) {
            val backImageUri: Uri = data.data!!
            change_profile_backimage.setImageURI(backImageUri)
        }

    }
}


