package com.example.fetchapikotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchapikotlin.R
import com.example.fetchapikotlin.model.Item

class ItemAdapter(private val items: List<Item>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemId: TextView = itemView.findViewById(R.id.item_id)
        private val listId: TextView = itemView.findViewById(R.id.list_id)
        private val name: TextView = itemView.findViewById(R.id.name)

        fun bind(item: Item) {
            itemId.text = item.id.toString()
            listId.text = item.listId.toString()
            name.text = item.name
        }
    }
}