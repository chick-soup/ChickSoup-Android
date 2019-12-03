package com.example.kakaotalk_dms.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.model.BanUser
import com.example.kakaotalk_dms.model.HideUser
import com.example.kakaotalk_dms.ui.adapter.BanAdapter
import com.example.kakaotalk_dms.ui.adapter.HideAdapter
import kotlinx.android.synthetic.main.fragment_ban.*
import kotlinx.android.synthetic.main.fragment_hide.*

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

        hideAdapter.add(HideUser("a", "dd"))
        hideAdapter.add(HideUser("a", ""))
        hideAdapter.add(HideUser("a", "dd"))
        hideAdapter.add(HideUser("a", "dd"))
        hideAdapter.add(HideUser("a", "dd"))
        hideAdapter.add(HideUser("a", ""))
        hideAdapter.add(HideUser("ab", "dd"))

        hide_back_btn.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
                ?.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            transaction?.replace(R.id.main_frame, SettingFragment())
            transaction?.commit()
        }
    }
}
