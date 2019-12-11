package com.example.kakaotalk_dms.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.ui.activity.SignInActivity
import com.example.kakaotalk_dms.util.UtilClass
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_setting, container, false)

        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hide_list.setOnClickListener{
            val transaction = activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.slide_in_bottom,R.anim.fade_out)
            transaction?.replace(R.id.main_frame,HideFragment())
            transaction?.addToBackStack(null)
            transaction?.commit()

        }
        check_id.setOnClickListener{
            val changeIdDialogFragment = ChangeIdDialogFragment.getInstance()
            changeIdDialogFragment.show(activity!!.supportFragmentManager, ChangeIdDialogFragment.TAG_CHANGE_ID_DIALOG)
        }
        ban_list.setOnClickListener{
            val transaction = activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.slide_in_bottom,R.anim.fade_out)
            transaction?.replace(R.id.main_frame,BanFragment())
            transaction?.commit()
        }
        logout_card.setOnClickListener {
            UtilClass.clearToken(activity!!.applicationContext)
            activity!!.finish()
        }
    }
}




