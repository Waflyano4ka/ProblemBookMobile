package com.example.problembookmobile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.problembookmobile.models.Tasks
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class TasksAdapter(val context : Context, val listTasks : ArrayList<Tasks>,
                   val listener : (Tasks) -> Unit) : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val projectName = view.findViewById<TextView>(R.id.ProjectName)
        val projectTime = view.findViewById<TextView>(R.id.TimeText)
        val imageTask = view.findViewById<ShapeableImageView>(R.id.ImageView3)

        fun bindView(task : Tasks, listener: (Tasks) -> Unit){
            projectName.text = task.name
            projectTime.text = if (task.enableTime) task.datetime else "Не назначено"
            Picasso.get().load(task.author.image).into(imageTask)
            itemView.setOnClickListener { listener(task) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.task_card, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(listTasks[position], listener)
    }

    override fun getItemCount(): Int = listTasks.size
}