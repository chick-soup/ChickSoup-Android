package com.example.kakaotalk_dms.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.model.SearchFriendUser

class SearchFriendAdapter:RecyclerView.Adapter<SearchFriendAdapter.Holder>() {
    private val newList = ArrayList<SearchFriendUser>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_friend,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int = newList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.connect(newList[position])
    }
    fun add(data:SearchFriendUser){
        newList.add(data)
        notifyDataSetChanged()
    }

    inner class Holder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        private val new_friend_nick = itemView.findViewById<TextView>(R.id.new_friends_nick)
        private val new_friend_image = itemView.findViewById<ImageView>(R.id.new_friends_image)

        fun connect(data: SearchFriendUser) {
            if (data.image != "") {
                new_friend_image?.setImageResource(R.drawable.kakaotalk_logo)
            } else
                new_friend_image?.setImageResource((R.mipmap.ic_launcher))

            new_friend_nick.text = data.nick
        }
    }
}