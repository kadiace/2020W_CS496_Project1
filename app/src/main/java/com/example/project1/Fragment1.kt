package com.example.project1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.fragment_1.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment1 : Fragment() {
    private var bookDataList : ArrayList<PhoneBookData>? = BookDataList.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_1, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener {
            // Change Activity.
            val intent = Intent(context, AddActivity::class.java)
            activity?.startActivityForResult(intent, 0)
        }

        // adapting recyclerview.
        phone_book_list.layoutManager = LinearLayoutManager(context)
        val search = search_name.text.toString()
        phone_book_list.adapter = bookDataList?.let { it -> context?.let { it1 -> PhoneBookListAdapter(search, it1, it) } }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        bookDataList = BookDataList.getInstance()
        phone_book_list.layoutManager = LinearLayoutManager(context)
        phone_book_list.adapter = bookDataList?.let { it -> context?.let { it1 -> PhoneBookListAdapter(
            null.toString(), it1, it) } }
    }
}
