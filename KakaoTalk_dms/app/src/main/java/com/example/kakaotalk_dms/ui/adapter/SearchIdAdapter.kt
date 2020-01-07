package com.example.kakaotalk_dms.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.Retrofit
import com.example.kakaotalk_dms.data.IDUser
import com.example.kakaotalk_dms.model.SearchUserId
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class SearchIdAdapter : RecyclerView.Adapter<SearchIdAdapter.Holder>(){

    private val idList = ArrayList<SearchUserId>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_id,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int = idList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.connect(idList[position])
    }

    fun add(data: SearchUserId){
        idList.add(data)
        notifyDataSetChanged()
    }
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val newId = itemView.findViewById<TextView>(R.id.new_id)
        private val newImage = itemView.findViewById<ImageView>(R.id.new_image)

        fun connect(data: SearchUserId){
            if(data.image != ""){
                newImage?.setImageResource(R.drawable.kakaotalk_logo)
            }
            else
                newImage?.setImageResource((R.mipmap.ic_launcher))

            newId.text = data.nick

            /*itemView.setOnClickListener {
                val call = Retrofit().service.postFriendsID("ds")
                call.enqueue(object: retrofit2.Callback<Unit>{
                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        Log.e("Add Friend", t.message.toString())
                    }
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        Log.d("Add Friend", response.code().toString())
                    }

                })
            }*/
        }
    }

}
