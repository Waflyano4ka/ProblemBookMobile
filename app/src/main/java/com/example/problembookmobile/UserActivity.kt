package com.example.problembookmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.problembookmobile.helpers.UserHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.paperdb.Paper

class UserActivity : AppCompatActivity() {

    private lateinit var frameLayout : FrameLayout
    private lateinit var bottomMenu : BottomNavigationView
    private var projectFragment = ProjectFragment()
    private var accountFragment = AccountFragment()
    private var archiveFragment = ArchiveFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        Paper.init(this)

        frameLayout = findViewById(R.id.frame_layout)
        bottomMenu = findViewById(R.id.bottom_menu)

        setFragment(projectFragment)

        bottomMenu.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item_project -> setFragment(projectFragment)
                R.id.item_archive -> setFragment(archiveFragment)
                R.id.item_account -> setFragment(accountFragment)
            }
            true
        }

    }

    private fun setFragment(fragment: Fragment){
        if (fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, fragment)
            transaction.commit()
        }
    }
}