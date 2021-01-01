package com.example.project1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.phonebook.view.*


class PhoneBookViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    var view : View = v

    fun bind(item: PhoneBookData) {
        if (item.name == null)
            view.name.text = "null"
        else
            view.name.text = item.name
        if (item.number == null)
            view.number.text = "null"
        else
            view.number.text = item.number
    }
}

class PhoneBookListAdapter(val itemList: List<PhoneBookData>) : RecyclerView.Adapter<PhoneBookViewHolder>() {
    override fun getItemCount() : Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneBookViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(
            com.example.project1.R.layout.phonebook,
            parent,
            false
        )
        return PhoneBookViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: PhoneBookViewHolder, position: Int) {
        val item = itemList[position]
        holder.apply {
            bind(item)
        }
        holder.itemView.setOnClickListener{
            Snackbar.make(holder.itemView, "힝 속았지", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }
    }
}


