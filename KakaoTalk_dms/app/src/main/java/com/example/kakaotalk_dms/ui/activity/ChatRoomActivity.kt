package com.example.kakaotalk_dms.ui.activity

import android.content.Context
import android.os.Bundle
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
import com.example.kakaotalk_dms.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_chat_content.*
import kotlinx.android.synthetic.main.chatroom_appbar.*
import org.jetbrains.anko.toast


class ChatRoomActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    private val drawerLayout: DrawerLayout? = null
    private var i = 0
    private var j = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        media_view.visibility = View.GONE
        imotion_view.visibility = View.GONE
        setSupportActionBar(chatroom_bar)
        initLayout()

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
    }
    override fun onClick(v: View?){
        when(v?.id){
            R.id.plus_media_btn->{
                i = 1 - i
                if (i == 1) {
                    if(j == 1) j -= 1
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
            R.id.plus_imotion_btn->{
                j = 1 - j
                if(j == 1){
                    if(i == 1) i -= 1
                    plus_imotion_btn.setImageResource(R.drawable.plus_imotion_selected)
                    plus_media_btn.setImageResource(R.drawable.plus_media_image)
                    media_view.visibility = View.VISIBLE
                    imotion_view.visibility = View.VISIBLE
                    imotion_view.hideKeyboard()
                }
                else{
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
                Log.d("hamburger", "eat")
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
    fun View.hideKeyboard(){
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}

