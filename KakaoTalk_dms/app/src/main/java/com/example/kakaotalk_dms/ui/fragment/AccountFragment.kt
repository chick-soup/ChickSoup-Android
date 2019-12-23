package com.example.kakaotalk_dms.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.Retrofit
import com.example.kakaotalk_dms.data.Profile
import com.example.kakaotalk_dms.util.UtilClass
import kotlinx.android.synthetic.main.fragment_account.*
import org.jetbrains.anko.image
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountFragment : Fragment() {

    private lateinit var mPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getProfileInfo()

        account_setting.setOnClickListener {
            val transaction = activity!!.supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_bottom,R.anim.fade_out)
            transaction.replace(R.id.main_frame,ChangeProfileFragment())
            transaction.commit()
        }

//        val args: Bundle? = arguments
//        if(args != null)
//        {
//            val mNick: String? = args.getString("nick")
//            val state_message:String? = args.getString("state_message")
//
//            account_name.text = mNick
//            account_message.text = state_message
//        }
    }
    fun getProfileInfo(){
        val jwt = UtilClass.getToken(activity!!.applicationContext)
        val call = Retrofit().service.getProfile(jwt,"","","")

        call.enqueue(object : Callback<Profile> {
            override fun onFailure(call: Call<Profile>, t: Throwable) {
             Log.e("can't getProfile",t.message.toString())
            }

            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                Log.d("AccountCode",response.code().toString())
                if(response.code() == 200){
                    account_name.text = response.body()?.userNick
                    account_message.text = response.body()?.userMessage

                    Log.d("imageId",response.body()?.imageId.toString())
                }
            }

        })
    }

}
