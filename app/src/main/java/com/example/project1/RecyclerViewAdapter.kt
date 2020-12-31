package com.example.project1

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.phonebook.view.*


//class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
//    val  = arrayListOf<PhoneBookData>()
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
//        // 아이템 레이아웃을 객체화
//        var v: View
//        v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
//
//        return ViewHolder(v)
//    }
//
//    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
//        parent.inflate(R.layout.item)
//    )
//
//    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
//    }
//
//}
//
//class ViewHolder(itemView: View) {
//    fun setItems(item: RecyclerViewData) {
//        //item 으로 데이터를 가져와 View에 Setting
//
//    }
//}

class PhoneBookViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    var view : View = v

    fun bind(item: PhoneBookData) {
        view.name.text = item.name
        view.number.text = item.number
    }
}

class PhoneBookListAdapter(val itemList : List<PhoneBookData>) : RecyclerView.Adapter<PhoneBookViewHolder>() {
    override fun getItemCount() : Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneBookViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(com.example.project1.R.layout.phonebook, parent, false)
        return PhoneBookViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: PhoneBookViewHolder, position: Int) {
        val item = itemList[position]
        holder.apply {
            bind(item)
        }
    }
}


