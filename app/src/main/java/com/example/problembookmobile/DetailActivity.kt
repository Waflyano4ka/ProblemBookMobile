package com.example.problembookmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.problembookmobile.databinding.ActivityDetailBinding
import com.example.problembookmobile.helpers.UserHelper
import com.example.problembookmobile.models.Projects
import com.example.problembookmobile.models.Tasks
import com.example.problembookmobile.services.ApiInterface
import com.example.problembookmobile.services.ServiceBuilder
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val itemJson : String? = intent.getStringExtra("PROJECT")
        val g = Gson()
        val item = g.fromJson(itemJson, Projects::class.java)
        binding.NameTaskText.text = item!!.name
        binding.NameText.text = item.user.name
        Picasso.get().load(item.user.image).into(binding.imageView2)

        getListTasks(item.id)
    }

    private fun getListTasks(id: Int) {
        val serviceBuilder = ServiceBuilder.buildService(ApiInterface::class.java)
        val requestCall = serviceBuilder.getTasks(id.toLong(), UserHelper.getUser()!!.mobileToken)
        requestCall.enqueue(object : Callback<ArrayList<Tasks>>{
            override fun onResponse(
                call: Call<ArrayList<Tasks>>,
                response: Response<ArrayList<Tasks>>
            ) {
                if (response.isSuccessful){
                    binding.listTasks.layoutManager = LinearLayoutManager(this@DetailActivity)
                    binding.listTasks.setHasFixedSize(true)
                    binding.listTasks.adapter = TasksAdapter(this@DetailActivity, response.body()!!){
                        val intent = Intent(this@DetailActivity, DetailTaskActivity::class.java)
                        val g = Gson()
                        intent.putExtra("TASK", g.toJson(it))
                        startActivity(intent)
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<Tasks>>, t: Throwable) {
                Toast.makeText(this@DetailActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }
}