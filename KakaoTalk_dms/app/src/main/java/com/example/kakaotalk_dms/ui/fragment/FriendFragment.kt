package com.example.kakaotalk_dms.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.Retrofit
import com.example.kakaotalk_dms.data.Friend
import com.example.kakaotalk_dms.data.SignIn
import com.example.kakaotalk_dms.model.User
import com.example.kakaotalk_dms.ui.adapter.FriendAdapter
import com.example.kakaotalk_dms.util.UtilClass
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.dialog_id_fragment.*
import kotlinx.android.synthetic.main.fragment_friends.*
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Response
import java.lang.reflect.Array
import javax.security.auth.callback.Callback

class FriendFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_friends, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val friendAdapter = FriendAdapter()
        friendsRecycler.adapter = friendAdapter

        val lm = LinearLayoutManager(this.context!!)
        friendsRecycler.layoutManager = lm
        friendsRecycler.setHasFixedSize(true)

        val token = UtilClass.getToken(activity!!.applicationContext)
        Log.d("changeProfile",token)

        val call = Retrofit().service.getFriend(token)
        call.enqueue(object: retrofit2.Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("Friend_Fragment_error", t.message.toString())
            }
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                Log.d("success_get_friend_code", response.code().toString())
                if (response.code() == 200) {
                    val jobj = JSONArray(response.body())
                } else{
                    Log.d("Friend_Fragment_error", "this ain't 200")
                }
            }
        })

        id_search.setOnClickListener {
            val transaction = activity!!.supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_bottom,R.anim.fade_out)
            transaction.replace(R.id.main_frame,SearchIdFragment())
            transaction.commit()
        }


    }

}
