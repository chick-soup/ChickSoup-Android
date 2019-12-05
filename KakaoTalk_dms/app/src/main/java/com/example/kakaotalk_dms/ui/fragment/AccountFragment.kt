package com.example.kakaotalk_dms.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.kakaotalk_dms.R
import kotlinx.android.synthetic.main.fragment_account.*
import org.jetbrains.anko.image

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

        account_setting.setOnClickListener {
            val transaction = activity!!.supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_bottom,R.anim.fade_out)
            transaction.replace(R.id.main_frame,ChangeProfileFragment())
            transaction.commit()
        }

        val args: Bundle? = arguments
        if(args != null)
        {
            val mNick: String? = args.getString("nick")
            val state_message:String? = args.getString("state_message")

            account_name.text = mNick
            account_message.text = state_message
        }
    }

}
