package com.example.kakaotalk_dms.ui.fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.DialogFragment
import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.Retrofit
import com.example.kakaotalk_dms.data.KakaoId
import com.example.kakaotalk_dms.data.SignIn
import com.example.kakaotalk_dms.util.UtilClass
import kotlinx.android.synthetic.main.dialog_id_fragment.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChangeIdDialogFragment : DialogFragment() {

    companion object {
        const val TAG_CHANGE_ID_DIALOG = "dialog_change_id"
        fun getInstance(): ChangeIdDialogFragment = ChangeIdDialogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(
            R.layout.dialog_id_fragment,
            container,
            false
        )
        getProfile()

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkId_cancel_btn.setOnClickListener {
            dialog?.cancel()
        }
        clipboard.setOnClickListener {
            val checkId = checkId.text.toString()
            val clipboardManager =
                activity!!.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("checkId", checkId)
            clipboardManager.setPrimaryClip(clipData)

            activity!!.toast("클립보드에 복사되었습니다.")
        }
    }

    override fun onResume() {
        super.onResume()
        dialog?.setCancelable(false)
        val params = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as android.view.WindowManager.LayoutParams
    }

    fun getProfile() {
        val current_jwt = UtilClass.getToken(activity!!.applicationContext)

        val call = Retrofit().service.getKakaoId(current_jwt)

        call.enqueue(object : Callback<KakaoId> {
            override fun onFailure(call: Call<KakaoId>, t: Throwable) {
                Log.e("error", t.message.toString())
            }

            override fun onResponse(call: Call<KakaoId>, response: Response<KakaoId>) {
                Log.d("get_jwt", current_jwt)
                Log.d("success_getId", response.code().toString())
                if (response.code() == 200) {
                    checkId.setText(response.body()?.userKakaoId)
                } else if (response.code() == 403) {
                    Log.d("403", "dddd")
                    val refreshToken = UtilClass.getRefreshToken(activity!!.applicationContext)
                    Log.d("refreshToken", refreshToken)
                    val call = Retrofit().service.getRefreshToken(refreshToken)
                    call.enqueue(object : Callback<SignIn>   {
                        override fun onFailure(call: Call<SignIn>, t: Throwable) {
                            Log.e("refreshTokenFail",t.message.toString())
                        }

                        override fun onResponse(call: Call<SignIn>, response: Response<SignIn>) {
                            Log.d("userRefreshToken", response.body()!!.userToken)
                            Log.d("refresh",response.code().toString())
                            UtilClass.saveToken(activity!!.applicationContext,response.body()!!.userToken)
                            Log.d("saveToken",UtilClass.getToken(activity!!.applicationContext))
                            getProfile()
                        }
                    })
                }
            }

        })
    }

}