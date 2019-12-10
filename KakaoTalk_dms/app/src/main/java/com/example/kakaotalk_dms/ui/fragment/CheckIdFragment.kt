package com.example.kakaotalk_dms.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kakaotalk_dms.data.KakaoId
import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.Retrofit
import com.example.kakaotalk_dms.util.UtilClass
import kotlinx.android.synthetic.main.fragment_check_id.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckIdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_check_id, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        check_id_back.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
                ?.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            transaction?.replace(R.id.main_frame, SettingFragment())
            transaction?.commit()
        }
        val current_jwt= UtilClass.getToken(activity!!.applicationContext)
        Log.d("current_jwt",current_jwt)
        getProfile()
// Dialog
//        change_id_btn.setOnClickListener {
//            val changeIdDialogFragment = ChangeIdDialogFragment.getInstance()
//            changeIdDialogFragment.show(activity!!.supportFragmentManager, ChangeIdDialogFragment.TAG_CHANGE_ID_DIALOG)
//        }
    }
    fun getProfile(){
        val current_jwt= UtilClass.getToken(activity!!.applicationContext)

        val kakaoId = checkId.text.toString()

        val call = Retrofit().service.getKakaoId(current_jwt)

        call.enqueue(object : Callback<KakaoId>{
            override fun onFailure(call: Call<KakaoId>, t: Throwable) {
                Log.e("error",t.message.toString())
            }
            override fun onResponse(call: Call<KakaoId>, response: Response<KakaoId>) {
                Log.d("get_jwt",current_jwt)
                Log.d("success_getId",response.code().toString())
                if(response.code() == 200){
                    checkId.setText(response.body()?.userKakaoId)
                }
            }

        })
    }


}