package com.example.kakaotalk_dms.ui.activity

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.model.ChatContent
import com.example.kakaotalk_dms.model.ChatEntity
import com.example.kakaotalk_dms.ui.adapter.BanAdapter
import com.example.kakaotalk_dms.ui.adapter.ChatAdapter
import com.example.kakaotalk_dms.util.UtilClass
import com.google.android.material.navigation.NavigationView
import io.socket.client.IO
import kotlinx.android.synthetic.main.activity_chat_content.*
import kotlinx.android.synthetic.main.chatroom_appbar.*
import org.jetbrains.anko.toast
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.fragment_ban.*
import org.json.JSONObject
import java.net.URISyntaxException

class ChatRoomActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    View.OnClickListener {
    private val drawerLayout: DrawerLayout? = null
    private var i = 0
    private var j = 0
    private var socket: Socket? = null
    private val txt = "str"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        media_view.visibility = View.GONE
        imotion_view.visibility = View.GONE
        setSupportActionBar(chatroom_bar)
        initLayout()

        val chatAdapter = ChatAdapter()
        chatroom_list.adapter = chatAdapter

        val lm = LinearLayoutManager(applicationContext)
        chatroom_list.layoutManager = lm

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        chat_back_btn.setOnClickListener {
            finish()
        }
        hamburgerIcon.setOnClickListener {
            val drawerLayout = findViewById<DrawerLayout>(R.id.dl_main_drawer_root)
            drawerLayout.openDrawer(GravityCompat.END)
        }
        plus_media_btn.setOnClickListener(this)
        plus_imotion_btn.setOnClickListener(this)
        chatroom_editText.addTextChangedListener(textWatcher)

        chatroomLayout.setOnClickListener {
            chatroom_editText.clearFocus()
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(chatroom_editText.windowToken, 0)
        }

        try {
            socket = IO.socket("http://10.156.147.139:3000")
            socket?.connect()
        } catch (e: URISyntaxException) {
            Log.e("ChatRoom socket Error", e.reason)
        }

        socket?.on("realTimeChat", sendMessage(chatAdapter))

        plus_send_btn.setOnClickListener {
            val data = JSONObject()
            data.put("roomId", "16458")
            data.put("token", UtilClass.getToken(applicationContext))
            data.put("chat", chatroom_editText.text.toString())
            data.put("type", txt)
            socket?.emit("chatting", data)
            chatroom_editText.text = null
            Log.d("send chat log", data.toString())
        }
    }

    val textWatcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {

        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            plus_send_btn.setImageResource(R.drawable.icon_send)
            if (chatroom_editText.text.isEmpty()) plus_send_btn.setImageResource(R.drawable.hastag)
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.plus_media_btn -> {
                i = 1 - i
                if (i == 1) {
                    if (j == 1) j -= 1
                    plus_media_btn.setImageResource(R.drawable.plus_media_selected)
                    plus_imotion_btn.setImageResource(R.drawable.plus_imotion_btn)
                    media_view.visibility = View.VISIBLE
                    imotion_view.visibility = View.GONE
                    media_view.hideKeyboard()
                } else {
                    plus_media_btn.setImageResource(R.drawable.plus_media_image)
                    plus_imotion_btn.setImageResource(R.drawable.plus_imotion_btn)
                    media_view.visibility = View.GONE
                    imotion_view.visibility = View.GONE
                }
            }
            R.id.plus_imotion_btn -> {
                j = 1 - j
                if (j == 1) {
                    if (i == 1) i -= 1
                    plus_imotion_btn.setImageResource(R.drawable.plus_imotion_selected)
                    plus_media_btn.setImageResource(R.drawable.plus_media_image)
                    media_view.visibility = View.VISIBLE
                    imotion_view.visibility = View.VISIBLE
                    imotion_view.hideKeyboard()
                } else {
                    plus_imotion_btn.setImageResource(R.drawable.plus_imotion_btn)
                    imotion_view.visibility = View.GONE
                    media_view.visibility = View.GONE
                }
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuitem1 -> toast("1번 클릭")
            R.id.menuitem2 -> toast("2번 클릭")
        }
        drawerLayout?.closeDrawer(GravityCompat.END)
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val drawerLayout = findViewById<DrawerLayout>(R.id.dl_main_drawer_root)
        when (item.itemId) {
            R.id.hamburgerIcon -> {
                drawerLayout?.openDrawer(GravityCompat.END)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initLayout() {
        val ab: ActionBar? = supportActionBar
        ab?.title = null
        ab?.setDisplayShowCustomEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.chatroom_menu, menu)
        return true
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun sendMessage(chatAdapter: ChatAdapter): Emitter.Listener = Emitter.Listener { args ->
        Log.d(" sendMessage connected","connected")
        runOnUiThread {
            val chatData: JSONObject = args[0] as JSONObject
            Log.d("chat 내용", chatData.getString("chat").toString())
            ChatContent(chatData.getString("chat"))

            chatAdapter.addItem(ChatContent(chatData.getString("chat")))
        }
    }
}

