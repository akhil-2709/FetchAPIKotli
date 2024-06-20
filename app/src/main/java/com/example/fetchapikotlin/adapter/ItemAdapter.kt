package com.example.fetchapikotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchapikotlin.R
import com.example.fetchapikotlin.model.Item

class ItemAdapter(context: Context, private val items: List<Item>) :
    ArrayAdapter<Item>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)

        val item = items[position]

        val listIdTextView = view.findViewById<TextView>(R.id.listIdTextView)
        val idTextView = view.findViewById<TextView>(R.id.idTextView)
        val nameTextView = view.findViewById<TextView>(R.id.nameTextView)

        listIdTextView.text = "List ID: ${item.listId}"
        idTextView.text = "ID: ${item.id}"
        nameTextView.text = "Name: ${item.name}"

        return view
    }
}