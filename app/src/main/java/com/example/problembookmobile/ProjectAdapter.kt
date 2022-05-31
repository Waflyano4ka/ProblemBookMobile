package com.example.problembookmobile

import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.problembookmobile.models.Projects
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class ProjectAdapter(val context : Context, val list : ArrayList<Projects>, val listener : (Projects) -> Unit) : RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {

    class ViewHolder(view :  View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.projectName)
        val imageView = view.findViewById<ShapeableImageView>(R.id.imageView3)
        val textViewDaily = view.findViewById<TextView>(R.id.textView)
        val constrColor = view.findViewById<ConstraintLayout>(R.id.constr_color)

        fun bindView(item : Projects, listener: (Projects) -> Unit){
            itemView.setOnClickListener{
                listener(item)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.project_card, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (list[position].active){
            holder.textView.text = list[position].name
        }else{
            val content = list[position].name
            val spannableString1 = SpannableString(content)
            spannableString1.setSpan(StrikethroughSpan(),0,content.length,0)
            holder.textView.text = spannableString1
        }
        holder.textViewDaily.text = list[position].dailyMessage?.toString()
        Picasso.get().load(list[position].user.image).into(holder.imageView)
        holder.constrColor.setBackgroundColor(Color.parseColor(list[position].color))
        holder.bindView(list[position], listener)

    }

    override fun getItemCount(): Int = list.size
}