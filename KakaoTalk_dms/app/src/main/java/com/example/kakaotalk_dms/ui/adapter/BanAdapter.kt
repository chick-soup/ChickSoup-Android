package com.example.kakaotalk_dms.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.model.BanUser

class BanAdapter:RecyclerView.Adapter<BanAdapter.Holder>() {
    private val banList = ArrayList<BanUser>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ban_friend,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int = banList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.connect(banList[position])
    }

    fun add(data:BanUser){
        banList.add(data)
        notifyDataSetChanged()
    }
    inner class Holder(itemView: View) :RecyclerView.ViewHolder(itemView){
        private val ban_friends_nick = itemView.findViewById<TextView>(R.id.ban_friends_nick)
        private val ban_friends_image = itemView.findViewById<ImageView>(R.id.ban_friends_image)

        fun connect(data:BanUser){
            if(data.image != ""){
                ban_friends_image?.setImageResource(R.drawable.kakaotalk_logo)
            }
            else
                ban_friends_image?.setImageResource((R.mipmap.ic_launcher))

            ban_friends_nick.text = data.nick
        }
    }
}