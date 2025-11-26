package com.example.secondhomework_pages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(private val menuItems: List<MenuItem>) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iconLeft: ImageView = view.findViewById(R.id.iconLeft)
        val textViewTitle: TextView = view.findViewById(R.id.textViewTitle)
        val iconRight: ImageView = view.findViewById(R.id.iconRight)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = menuItems[position]
        holder.iconLeft.setImageResource(item.icon)
        holder.textViewTitle.text = item.title


        holder.itemView.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "点击了: ${item.title}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount() = menuItems.size
}

