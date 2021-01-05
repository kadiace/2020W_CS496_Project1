package com.example.project1

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_1.*
import kotlinx.android.synthetic.main.fragment_1.view.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment1 : Fragment() {
    private var bookDataList : ArrayList<PhoneBookData>? = BookDataList.getInstance()
    private var searchList = mutableListOf<PhoneBookData>()
    private var searchText: String = null.toString()

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

        bookDataList = BookDataList.getInstance()

        setSearchListener(search_name)

        phone_book_list.layoutManager = LinearLayoutManager(context)
        phone_book_list.adapter = context?.let { it1 -> PhoneBookListAdapter(it1,
            searchList as ArrayList<PhoneBookData>) }
    }

    private fun setSearchListener(view: View) {
        view.search_name.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchText = s.toString()
                searchList = bookDataList?.let { getPhoneNumbers(searchText, it) } as MutableList<PhoneBookData>
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        bookDataList = BookDataList.getInstance()

        setSearchListener(search_name)

        phone_book_list.layoutManager = LinearLayoutManager(context)
        phone_book_list.adapter = context?.let { it1 -> PhoneBookListAdapter(it1,
            searchList as ArrayList<PhoneBookData>) }
    }

    fun getPhoneNumbers(name:String, datalist : ArrayList<PhoneBookData>) : List<PhoneBookData> {

        // init value
        var resultList = mutableListOf<PhoneBookData>()

        if (name.isNotEmpty()) {
            for (i in 0..datalist!!.size-1) {
                if (datalist?.get(i)?.name!!.contains(name,false)) {
                    resultList.add(datalist?.get(i)!!)
                }
            }
        } else {
            resultList = datalist!!
        }
        return resultList
    }
}
