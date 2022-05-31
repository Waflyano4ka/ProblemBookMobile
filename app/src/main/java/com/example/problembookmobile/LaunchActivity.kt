package com.example.problembookmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.problembookmobile.helpers.UserHelper
import io.paperdb.Paper

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        Paper.init(this)

        Handler(Looper.getMainLooper()).postDelayed({
        val user = UserHelper.getUser()
        if (user == null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            val intentUser = Intent(this, UserActivity::class.java)
            startActivity(intentUser)
            finish()
        }
        },3000)

    }
}