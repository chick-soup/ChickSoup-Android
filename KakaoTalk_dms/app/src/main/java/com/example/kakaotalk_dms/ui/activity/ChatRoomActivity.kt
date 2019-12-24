package com.example.kakaotalk_dms.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.kakaotalk_dms.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_chat_content.*
import kotlinx.android.synthetic.main.chatroom_appbar.*
import org.jetbrains.anko.toast


class ChatRoomActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    private var i = 0
    private val drawerLayout: DrawerLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        extra_view.visibility = View.GONE
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
    }
    override fun onClick(v: View?){
        when(v?.id){
            R.id.plus_media_btn->{
                Log.d("click", "$i")
                i = 1 - i
                if (i == 1) {
                    plus_media_btn.setImageResource(R.drawable.yellow_plus_media)
                    extra_view.visibility = View.VISIBLE
                } else {
                    plus_media_btn.setImageResource(R.drawable.plus_media)
                    extra_view.visibility = View.GONE
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
        Log.d("initLayout", "넘어왔네요")
        //val drawerLayout = findViewById<DrawerLayout>(R.id.dl_main_drawer_root)

        //val chatroom_bar = findViewById<Toolbar>(R.id.chatroom_bar)
        val ab: ActionBar? = supportActionBar
        ab?.title = null
        ab?.setDisplayShowCustomEnabled(true)

        // val drawerToggle = ActionBarDrawerToggle(this,drawerLayout,chatroom_bar,
        //   R.string.drawer_open, R.string.drawer_close)
//        drawerLayout.addDrawerListener(drawerToggle)
//        drawerToggle.syncState()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.chatroom_menu, menu)
        return true
    }
}

