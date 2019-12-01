package com.example.kakaotalk_dms.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.model.User

class FriendAdapter : RecyclerView.Adapter<FriendAdapter.Holder>() {

    private val userList = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_friend, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.connect(userList[position])
    }

    fun add(data: User) {
        userList.add(data)
        notifyDataSetChanged()
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val friendsImage = itemView.findViewById<ImageView>(R.id.friends_image)
        private val nick = itemView.findViewById<TextView>(R.id.friends_nick)
        private val message = itemView.findViewById<TextView>(R.id.state_message)

        fun connect(data: User) {
            if (data.image != "")
                friendsImage?.setImageResource(R.drawable.kakaotalk_logo)
            else
                friendsImage?.setImageResource(R.mipmap.ic_launcher)

            nick?.text = data.nick
            message?.text = data.message
        }
    }
}
