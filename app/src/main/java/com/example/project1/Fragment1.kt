package com.example.project1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_1.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment1 : Fragment() {
    private val bookDataList : ArrayList<PhoneBookData>? = BookDataList.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_1, container, false)

        val bundle : Bundle? = getArguments()

        val type = bundle?.getInt("type")

        when(type){
            0-> {
                val name = bundle?.getString("name")
                val number = bundle?.getString("number")
                if (name != "" && number !="" && name != null && number != null) {
                    val data: PhoneBookData = PhoneBookData(name, number)
                    bookDataList?.add(data)
                    Collections.sort(bookDataList)
                }
                else {
                    Snackbar.make(view, "이름과 번호를 정확히 입력해주세요!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show()
                }
            }
            1-> {
                val name = bundle?.getString("name")
                val number = bundle?.getString("number")
                val position = bundle?.getInt("position")
                if (name != "" && number !="" && name != null && number != null) {
                    val data: PhoneBookData = PhoneBookData(name, number)
                    bookDataList?.set(position, data)
                    Collections.sort(bookDataList)
                }
                else {
                    Snackbar.make(view, "이름과 번호를 정확히 입력해주세요!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show()
                }
            }
            2-> {
                val position = bundle?.getInt("position")
                bookDataList?.removeAt(position)
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener {

            // Change Activity.
            val intent = Intent(context, AddActivity::class.java)
            startActivity(intent)
        }

        // adapting recyclerview.
        phone_book_list.layoutManager = LinearLayoutManager(context)
        phone_book_list.adapter = bookDataList?.let { it -> context?.let { it1 -> PhoneBookListAdapter(it1, it) } }
    }
}
