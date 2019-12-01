package com.example.kakaotalk_dms.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kakaotalk_dms.R
import kotlinx.android.synthetic.main.fragment_check_id.*

class CheckIdFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_check_id, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       check_id_back.setOnClickListener {
               val transaction = activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.fade_in,R.anim.fade_out)
               transaction?.replace(R.id.main_frame,SettingFragment())
               transaction?.commit()
       }
// Dialog
//        change_id_btn.setOnClickListener {
//            val changeIdDialogFragment = ChangeIdDialogFragment.getInstance()
//            changeIdDialogFragment.show(activity!!.supportFragmentManager, ChangeIdDialogFragment.TAG_CHANGE_ID_DIALOG)
//        }
    }
}