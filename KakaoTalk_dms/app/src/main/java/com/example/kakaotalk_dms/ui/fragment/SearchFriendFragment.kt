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

        val newIdAdapter = SearchFriendAdapter()
        search_friend_list.adapter  = newIdAdapter

        val lm = LinearLayoutManager(this.context!!)
        search_friend_list.layoutManager = lm
        search_friend_list.setHasFixedSize(true)

        newIdAdapter.add(SearchFriendUser("a",""))
        newIdAdapter.add(SearchFriendUser("b",""))
        newIdAdapter.add(SearchFriendUser("c",""))
        newIdAdapter.add(SearchFriendUser("d",""))
        newIdAdapter.add(SearchFriendUser("e",""))
        newIdAdapter.add(SearchFriendUser("f",""))
        newIdAdapter.add(SearchFriendUser("a",""))
        newIdAdapter.add(SearchFriendUser("a",""))
        newIdAdapter.add(SearchFriendUser("a",""))
        newIdAdapter.add(SearchFriendUser("a",""))

        search_friend_back_btn.setOnClickListener {
            val transaction = activity!!.supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out)
            transaction.replace(R.id.main_frame,FriendFragment())
            transaction.commit()
        }
    }
}
