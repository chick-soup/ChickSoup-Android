package com.example.kakaotalk_dms.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.model.SearchFriendUser
import com.example.kakaotalk_dms.ui.adapter.SearchFriendAdapter
import kotlinx.android.synthetic.main.fragment_search_friend.*

class SearchFriendFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search_friend, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newFriendAdapter = SearchFriendAdapter()
        search_friend_list.adapter  = newFriendAdapter

        val lm = LinearLayoutManager(this.context!!)
        search_friend_list.layoutManager = lm
        search_friend_list.setHasFixedSize(true)

        newFriendAdapter.add(SearchFriendUser("a",""))
        newFriendAdapter.add(SearchFriendUser("b",""))
        newFriendAdapter.add(SearchFriendUser("c",""))
        newFriendAdapter.add(SearchFriendUser("d",""))
        newFriendAdapter.add(SearchFriendUser("e",""))
        newFriendAdapter.add(SearchFriendUser("f",""))
        newFriendAdapter.add(SearchFriendUser("a",""))
        newFriendAdapter.add(SearchFriendUser("a",""))
        newFriendAdapter.add(SearchFriendUser("a",""))
        newFriendAdapter.add(SearchFriendUser("a",""))

        search_friend_back_btn.setOnClickListener {
            val transaction = activity!!.supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out)
            transaction.replace(R.id.main_frame,FriendFragment())
            transaction.commit()
        }
    }
}
