package com.example.kakaotalk_dms.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.model.SearchUserId
import com.example.kakaotalk_dms.ui.adapter.SearchIdAdapter
import kotlinx.android.synthetic.main.fragment_search_id.*

class SearchIdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_id, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newIdAdapter = SearchIdAdapter()
        searchId_list.adapter  = newIdAdapter

        val lm = LinearLayoutManager(this.context!!)
        searchId_list.layoutManager = lm
        searchId_list.setHasFixedSize(true)

        newIdAdapter.add(SearchUserId("a",""))
        newIdAdapter.add(SearchUserId("b",""))
        newIdAdapter.add(SearchUserId("c",""))
        newIdAdapter.add(SearchUserId("d",""))
        newIdAdapter.add(SearchUserId("e",""))
        newIdAdapter.add(SearchUserId("f",""))
        newIdAdapter.add(SearchUserId("a",""))
        newIdAdapter.add(SearchUserId("a",""))
        newIdAdapter.add(SearchUserId("a",""))
        newIdAdapter.add(SearchUserId("a",""))

        search_id_back_btn.setOnClickListener {
            val transaction = activity!!.supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out)
            transaction.replace(R.id.main_frame,FriendFragment())
            transaction.commit()
        }
    }
}

