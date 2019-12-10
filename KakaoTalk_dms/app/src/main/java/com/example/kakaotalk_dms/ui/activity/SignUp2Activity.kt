package com.example.kakaotalk_dms.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kakaotalk_dms.*
import kotlinx.android.synthetic.main.activity_sign_up2.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class SignUp2Activity : AppCompatActivity() {

    val GET_FIRST_IMAGE = 0
    private var profileImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up2)

        compelete_signup_btn.setOnClickListener {
            uploadFile()
            startActivity<SignInActivity>()
        }
        first_profile_image.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            startActivityForResult(intent, GET_FIRST_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GET_FIRST_IMAGE && resultCode == RESULT_OK && data != null && data.data != null) {
            profileImageUri = data.data
            first_profile_image.setImageURI(profileImageUri)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun uploadFile() {
        Log.d("uploadFile",profileImageUri.toString())
        if (profileImageUri != null) {
            val fileUri: String = realPath(profileImageUri!!)
            uploadToServer(fileUri)

            Log.d("profileImageUri",profileImageUri.toString())
            Log.d("fileUri",fileUri)
        }
    }

    @SuppressLint("Recycle")
    fun realPath(contentUri: Uri): String {
        val cursor: Cursor? = this.contentResolver.query(contentUri, null, null, null, null)
        return if (cursor == null) {
            contentUri.path!!
        } else {
            cursor.moveToFirst()
            val idx: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            cursor.getString(idx)
        }
    }

    fun uploadToServer(uri: String) {
        Log.d("uploadToServer", uri)
        val nickname = first_nick.text.toString()
        val file = File(uri)
        val requestFile: RequestBody =
            RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val multipartBody: MultipartBody.Part =
            MultipartBody.Part.createFormData("file", file.name, requestFile)
        val requestBody: RequestBody = RequestBody.create(MediaType.parse("text/plain"),nickname)

        Log.d("requestFile", "$multipartBody | $requestBody" )
        val prefs =
            getSharedPreferences("com.example.kakaotalk_dms.ui.SignUp1Activity", MODE_PRIVATE)
        val current_jwt = prefs.getString("token", "").toString()

        val call = Retrofit().service.sendFirstProfile(current_jwt, multipartBody, requestBody)

        call.enqueue(object : Callback<UploadSuccess> {
            override fun onFailure(call: Call<UploadSuccess>, t: Throwable) {
                Log.e("uploadFail",t.message.toString())
            }

            override fun onResponse(call: Call<UploadSuccess>, response: Response<UploadSuccess>) {
                Log.d("successLoad",response.code().toString())
            }

        })
    }
}
