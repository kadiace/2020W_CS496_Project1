package com.example.project1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_1.*


/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment1 : Fragment() {
    private val BookDataList = listOf<PhoneBookData>(
        PhoneBookData("john", "010-2259-1802")
    )

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
            val intent = Intent(context, AddActivity::class.java)
            startActivity(intent)
        }

//        fab.setOnClickListener {
//            Toast.makeText(this, "플로팅 버튼 클릭", Toast.LENGTH_SHORT).show()
//        }

        // use a linear layout manager
        phone_book_list.layoutManager = LinearLayoutManager(context)

        // specify an adapter (see also next example)
        phone_book_list.adapter = PhoneBookListAdapter(BookDataList)
    }
}
