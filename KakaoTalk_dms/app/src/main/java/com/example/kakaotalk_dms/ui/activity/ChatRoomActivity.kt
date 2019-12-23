package com.example.kakaotalk_dms.ui.activity

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.kakaotalk_dms.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.chatroom_appbar.*
import org.jetbrains.anko.toast


class ChatRoomActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val drawerLayout: DrawerLayout? = null
    private val drawerToggle:ActionBarDrawerToggle?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        setSupportActionBar(chatroom_bar)
        val navigationView = findViewById<NavigationView>(R.id.nv_main_navigation_root)
        navigationView.setNavigationItemSelectedListener(this)
        initLayout()
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuitem1 -> toast("1번 클릭")
            R.id.menuitem2 -> toast("2번 클릭")
        }
        drawerLayout?.closeDrawer(GravityCompat.END)
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(drawerToggle!!.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initLayout(){
        Log.d("initLayout","넘어왔네요")
        val drawerLayout = findViewById<DrawerLayout>(R.id.dl_main_drawer_root)
        val navigationView = findViewById<NavigationView>(R.id.nv_main_navigation_root)

        val chatroom_bar = findViewById<Toolbar>(R.id.chatroom_bar)
        val ab: ActionBar? = supportActionBar
        ab?.title = null
//        ab?.setDisplayUseLogoEnabled(true)
//        ab?.setDisplayShowHomeEnabled(true)
        ab?.setDisplayHomeAsUpEnabled(true)
        ab?.setHomeAsUpIndicator(R.drawable.bar_icon)


        val drawerToggle = ActionBarDrawerToggle(this,drawerLayout,chatroom_bar,
            R.string.drawer_open, R.string.drawer_close)
        drawerLayout.addDrawerListener(drawerToggle)
        navigationView.setNavigationItemSelectedListener(this)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.chatroom_menu, menu)
        return true
    }
}
