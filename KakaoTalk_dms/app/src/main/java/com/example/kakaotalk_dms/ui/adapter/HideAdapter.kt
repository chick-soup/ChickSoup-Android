package com.example.kakaotalk_dms.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.model.BanUser
import com.example.kakaotalk_dms.model.HideUser

class HideAdapter:RecyclerView.Adapter<HideAdapter.Holder>() {
    private val hideList = ArrayList<HideUser>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ban_friend,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int = hideList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.connect(hideList[position])
    }

    fun add(data: HideUser){
        hideList.add(data)
        notifyDataSetChanged()
    }
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val hide_friends_nick = itemView.findViewById<TextView>(R.id.hide_friends_nick)
        private val hide_friends_image = itemView.findViewById<ImageView>(R.id.hide_friends_image)

        fun connect(data: HideUser){
            if(data.image != ""){
                hide_friends_image?.setImageResource(R.drawable.kakaotalk_logo)
            }
            else
                hide_friends_image?.setImageResource((R.mipmap.ic_launcher))

            hide_friends_nick?.text = data.nick
        }
    }
}