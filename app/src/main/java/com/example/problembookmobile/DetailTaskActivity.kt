package com.example.problembookmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.problembookmobile.databinding.ActivityDetailTaskBinding
import com.example.problembookmobile.models.Tasks
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class DetailTaskActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_task)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_task)

        val itemJson = intent.getStringExtra("TASK")
        val g = Gson()
        val item = g.fromJson(itemJson, Tasks::class.java)

        binding.NameTaskText.text = item.name
        Picasso.get().load(item.author.image).into(binding.imageView2)
        binding.CreateTime.text = item.createDatetime.split('T')[0]
        binding.EditTime.text = if (item.editDatetime != null) item.editDatetime.toString() else "-"
        binding.DateEndText.text = if (item.enableTime) "Срок сдачи: " + item.editDatetime else "Срок сдачи: не задан"
        binding.DescriptionText.text = item.description
    }
}