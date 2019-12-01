package com.example.kakaotalk_dms.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.kakaotalk_dms.R
import com.example.kakaotalk_dms.ui.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().run {
            replace(R.id.main_frame, FriendFragment())
            commit()
        }

        main_menu.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }


    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val transaction = supportFragmentManager.beginTransaction()
            when (item.itemId) {
                R.id.friends -> {
                    transaction.replace(
                        R.id.main_frame,
                        FriendFragment()
                    )
                    transaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.chat -> {
                    transaction.replace(
                        R.id.main_frame,
                        ChatFragment()
                    )
                    transaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.account -> {
                    transaction.replace(
                        R.id.main_frame,
                        AccountFragment()
                    )
                    transaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.setting -> {
                    transaction.replace(
                        R.id.main_frame,
                        SettingFragment()
                    )
                    transaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

}
