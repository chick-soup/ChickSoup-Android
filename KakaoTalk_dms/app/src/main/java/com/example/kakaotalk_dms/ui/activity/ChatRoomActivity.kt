package com.example.kakaotalk_dms.ui.activity

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.kakaotalk_dms.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.chatroom_appbar.*
import org.jetbrains.anko.toast


class ChatRoomActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val drawerlayout = findViewById<DrawerLayout>(R.id.dl_main_drawer_root)
    val navigationView = findViewById<NavigationView>(R.id.nv_main_navigation_root)
    val drawerToggle:ActionBarDrawerToggle?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        initLayout()
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuitem1 -> toast("1번 클릭")
            R.id.menuitem2 -> toast("2번 클릭")
        }
        drawerlayout.closeDrawer(GravityCompat.END)
        return false
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle?.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(drawerToggle!!.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle?.onConfigurationChanged(newConfig)
    }

    private fun initLayout(){
        setSupportActionBar(chatroom_bar)
        supportActionBar?.title = "채팅방"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.mipmap.ic_launcher)

        val drawerToggle = ActionBarDrawerToggle(this,drawerlayout,chatroom_bar,
            R.string.drawer_open,R.string.drawer_close)
        drawerlayout.addDrawerListener(drawerToggle)
        navigationView.setNavigationItemSelectedListener(this)
    }
}
