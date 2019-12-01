package com.example.kakaotalk_dms.ui.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.model.BanUser
import com.example.kakaotalk_dms.ui.adapter.BanAdapter
import kotlinx.android.synthetic.main.fragment_ban.*

class BanFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_ban, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val banAdapter = BanAdapter()
        banRecyler.adapter = banAdapter

        val lm = LinearLayoutManager(this.context!!)
        banRecyler.layoutManager = lm
        banRecyler.setHasFixedSize(true)

        banAdapter.add(BanUser("a","dd"))
        banAdapter.add(BanUser("a",""))
        banAdapter.add(BanUser("a","dd"))
        banAdapter.add(BanUser("a","dd"))
        banAdapter.add(BanUser("a","dd"))
        banAdapter.add(BanUser("a",""))
        banAdapter.add(BanUser("ab","dd"))

        ban_back_btn.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(R.anim.fade_in,R.anim.fade_out)
            transaction?.replace(R.id.main_frame,SettingFragment())
            transaction?.commit()
        }

    }
}
