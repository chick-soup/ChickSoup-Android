package com.example.kakaotalk_dms.ui.fragment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.Retrofit
import com.example.kakaotalk_dms.data.Friend
import com.example.kakaotalk_dms.model.BanUser
import com.example.kakaotalk_dms.model.HideUser
import com.example.kakaotalk_dms.ui.adapter.BanAdapter
import com.example.kakaotalk_dms.ui.adapter.HideAdapter
import com.example.kakaotalk_dms.util.UtilClass
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_ban.*
import kotlinx.android.synthetic.main.fragment_hide.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

class HideFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hide, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hideAdapter = HideAdapter()
        hideRecyler.adapter = hideAdapter

        val lm = LinearLayoutManager(this.context!!)
        hideRecyler.layoutManager = lm
        hideRecyler.setHasFixedSize(true)

        val myFriend: ArrayList<Friend> = ArrayList()

        val call = Retrofit().service.getFriendsHidden(UtilClass.getToken(activity!!.applicationContext))
        call.enqueue(object: Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("fail", t.message.toString())
            }
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.e("suc", response.code().toString())
                Log.e("suc", response.isSuccessful.toString())
                Log.e("suc", response.body().toString())

                if(response.body() != null && response.body()!!.isJsonNull)
                    for(i in 1..response.body()!!.size()) {
                        val a = response.body()!!.get(i.toString()).asJsonObject
                        val fr = Friend(a.get("id").asString, a.get("nickname").asString, a.get("status_message").asString, a.get("mute").asString, a.get("hidden").asString, a.get("bookmark").asString)
                        myFriend.add(fr)
                    }
            }
        })

        val myFriendImgs: ArrayList<Uri> = ArrayList()
        for(i in myFriend){
            val call2 = Retrofit().service.getFriendsImg(i.id)
            call2.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    Log.e("Pro1", response?.body().toString())
                    if (response?.body() != null) {
                        val bytes = response.body()!!.bytes()
                        val bmp: Bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                        //BitmapFactory.decodeStream(response.body()!!.byteStream())
                        myFriendImgs.add(getImageUri(context, bmp))
                    }
                }
                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    Log.e("Pro1", t.toString())
                }
            })
        }

        if (myFriend.size != 0) {
            for (i in 0..myFriend.size) {
                hideAdapter.add(HideUser(myFriend[i].nickname, myFriendImgs[i].toString()))
            }
        }

        hide_back_btn.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
                ?.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            transaction?.replace(R.id.main_frame, SettingFragment())
            transaction?.commit()
        }
    }

    fun getImageUri(inContext: Context?, inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(inContext?.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }
}
