package com.example.kakaotalk_dms.ui.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.model.ChatEntity

class ChatAdapter:RecyclerView.Adapter<ChatAdapter.Holder>() {
    val chatList = ArrayList<ChatEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat,parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = chatList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(chatList[position])
    }
    fun addItem(item:ChatEntity?){
        if(item==null) return
        chatList.add(item)
        notifyItemChanged(chatList.size-1)
    }
    inner class Holder(val v: View):RecyclerView.ViewHolder(v){
        fun bind(item:ChatEntity){
            if(item.isMine()){
                moveToRight(v)
                backGroundYellow(v)
            }
            else{
                moveToLeft(v)
                backGroundWhite(v)
            }
            setText(v, item.toString())
        }
    }
    fun setText(v : View, str : String) {
        v.findViewById<TextView>(R.id.chat_chatting_tv).text = str
    }
    fun moveToRight(v : View) {
        v.findViewById<LinearLayout>(R.id.chat_background).gravity = Gravity.RIGHT
    }

    fun moveToLeft(v : View) {
        v.findViewById<LinearLayout>(R.id.chat_background).gravity = Gravity.LEFT
    }

    fun backGroundYellow(v : View) {
        v.findViewById<TextView>(R.id.chat_chatting_tv).backgroundTintList = ColorStateList.valueOf(
            Color.YELLOW)
    }

    fun backGroundWhite(v : View) {
        v.findViewById<TextView>(R.id.chat_chatting_tv).backgroundTintList = ColorStateList.valueOf(Color.WHITE)
    }


}