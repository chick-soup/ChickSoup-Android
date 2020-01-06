package com.example.kakaotalk_dms.ui.fragment

import android.os.Bundle
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        for(i in myFriend){
            hideAdapter.add(HideUser(i.nickname, i.id))
        }

        hide_back_btn.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
                ?.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            transaction?.replace(R.id.main_frame, SettingFragment())
            transaction?.commit()
        }
    }
}
