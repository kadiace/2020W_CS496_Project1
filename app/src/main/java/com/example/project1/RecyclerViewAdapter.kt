package com.example.project1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add.*
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

class PhoneBookListAdapter(val mContext: Context, val itemList: List<PhoneBookData>) : RecyclerView.Adapter<PhoneBookViewHolder>() {
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

            // Set context, intent.
            val context = holder.view.context
            val intent = Intent(context, ItemActivity::class.java)

            // Set variables for bundle
            val name = item.name
            val number = item.number

            // Bundle을 통해서 전달
            val bundle = Bundle()
            bundle.putString("name", name)
            bundle.putString("number", number)
            bundle.putInt("position", position)

            intent.putExtras(bundle)    // intent 객체에 Bundle을 저장

            mContext.startActivity(intent)
        }
    }
}


