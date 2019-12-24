package com.example.kakaotalk_dms.ui.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.Retrofit
import com.example.kakaotalk_dms.util.UtilClass
import kotlinx.android.synthetic.main.fragment_change_profile.*
import okhttp3.MediaType
import retrofit2.Callback
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response

class ChangeProfileFragment : Fragment() {

    private val GET_GALLERY_IMAGE: Int = 200
    private val GET_BACK_IMAGE: Int = 1

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
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            startActivityForResult(intent, GET_GALLERY_IMAGE)
        }
        change_backimage_btn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            startActivityForResult(intent, GET_BACK_IMAGE)
        }
        change_profile_check_btn.setOnClickListener {
            changeProfile()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.data != null) {
            val profileImageUri: Uri = data.data!!
            change_image.setImageURI(profileImageUri)
        } else if (requestCode == GET_BACK_IMAGE && resultCode == RESULT_OK && data != null && data.data != null) {
            val backImageUri: Uri = data.data!!
            change_profile_backimage.setImageURI(backImageUri)
        }
    }
    fun changeProfile() {
        val ifMobile = "mobile"
        val where:RequestBody =
            RequestBody.create(MediaType.parse("text/plain"),ifMobile)
        val nickname: RequestBody =
            RequestBody.create(MediaType.parse("text/plain"),change_nick_editText.text.toString())
        val statusMessage:RequestBody =
            RequestBody.create(MediaType.parse("text/plain"),change_message_editText.text.toString())
        val token = UtilClass.getToken(activity!!.applicationContext)
        Log.d("changeProfile",token)
        val call = Retrofit().service.changeProfile(token,nickname, statusMessage, where)
        call.enqueue(object :Callback<Void>{
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("onfailure",t.message)
            }
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.code() == 200) {
                    val fragment: Fragment = AccountFragment()
                    val fm: FragmentManager? = fragmentManager
                    val transaction: FragmentTransaction = fm!!.beginTransaction()
                    transaction.replace(R.id.main_frame, fragment).addToBackStack(null)
                        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out).commit()
                }
            }

        } )
    }
}


