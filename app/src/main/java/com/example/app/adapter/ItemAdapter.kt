package com.example.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.model.Pahlawan

class ItemAdapter (
    private val context: Context,
    private val data: List<Pahlawan>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.img_pahlawan)
        val name: TextView = view.findViewById(R.id.name_pahlawan)
        val birthday: TextView = view.findViewById(R.id.birthday_pahlawan)
        val birthplace: TextView = view.findViewById(R.id.birthplace_pahlawan)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemAdapter.ItemViewHolder, position: Int) {
        val item = data[position]
        holder.image.setImageResource(item.image)
        holder.name.text = item.name
        holder.birthday.text = item.birthday
        holder.birthplace.text = item.birthplace
    }

    override fun getItemCount(): Int {
        return data.size
    }
}