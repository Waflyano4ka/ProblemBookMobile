package com.example.problembookmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.problembookmobile.helpers.UserHelper
import com.example.problembookmobile.models.Users
import com.example.problembookmobile.services.ApiInterface
import com.example.problembookmobile.services.ServiceBuilder
import io.paperdb.Paper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var tokenText : EditText
    lateinit var authButton : AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Paper.init(this)

        tokenText = findViewById(R.id.TokenText)
        authButton = findViewById(R.id.loginButton)

        authButton.setOnClickListener {
            authByToken(tokenText.text.toString())
        }
    }

    private fun authByToken(token : String){
        val serviceBuilder = ServiceBuilder.buildService(ApiInterface::class.java)
        val requestCall = serviceBuilder.getUserByToken(token)
        requestCall.enqueue(object : Callback<ArrayList<Users>>{
            override fun onResponse(
                call: Call<ArrayList<Users>>,
                response: Response<ArrayList<Users>>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(this@MainActivity, "Добро пожаловать!", Toast.LENGTH_SHORT).show()
                    UserHelper.rememberUser(response.body()!!.last())
                    val intentGo = Intent(this@MainActivity, UserActivity::class.java)
                    startActivity(intentGo)
                    finish()
                }else{
                    Toast.makeText(this@MainActivity, "Введ неверный или неакивный токен", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<Users>>, t: Throwable) {
                //Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

        })
    }

}