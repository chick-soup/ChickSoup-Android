package com.example.kakaotalk_dms.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.ui.activity.ChatRoomActivity
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn.setOnClickListener {
            val intent =  Intent(activity,ChatRoomActivity::class.java)
            activity?.startActivity(intent)
        }
    }
}
