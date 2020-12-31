package com.example.project1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_1.*
import kotlinx.android.synthetic.main.fragment_1.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment1 : Fragment() {
    private val BookDataList = listOf(
        PhoneBookData("john", "010-2259-1802"),
        PhoneBookData("jain", "010-0232-5532"),
        PhoneBookData("jain", "010-0232-5532"),
        PhoneBookData("jain", "010-0232-5532"),
        PhoneBookData("jain", "010-0232-5532"),
        PhoneBookData("jain", "010-0232-5532"),
        PhoneBookData("jain", "010-0232-5532"),
        PhoneBookData("jain", "010-0232-5532"),
        PhoneBookData("jain", "010-0232-5532"),
        PhoneBookData("jain", "010-0232-5532"),
        PhoneBookData("nike", "010-3734-8621")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_1, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // use a linear layout manager
        phone_book_list.layoutManager = LinearLayoutManager(context)

        // specify an adapter (see also next example)
        phone_book_list.adapter = PhoneBookListAdapter(BookDataList)
    }
}