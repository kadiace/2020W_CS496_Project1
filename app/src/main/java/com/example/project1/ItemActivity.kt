package com.example.project1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make
import kotlinx.android.synthetic.main.activity_item.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_1.*
import java.util.ArrayList

class ItemActivity : AppCompatActivity() {

    var position : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        // 여러 인텐트를 구분하기 위한 result code
        val DELETE_CODE : Int = 2

        // AddActivity의 intent를 받아 저장.
        val intent = getIntent()
        val name = intent.getStringExtra("name")
        val number = intent.getStringExtra("number")
        position = intent.getIntExtra("position", 0)

        text_name.text = name
        text_number.text = number


        edit_button.setOnClickListener{

            // 화면전환 (intent 객체 생성), Edit
            val intent = Intent(this, EditActivity::class.java)

            // bundle 객체 생성, contents 저장
            val bundle = Bundle()
            bundle.putString("name", name)
            bundle.putString("number", number)
            bundle.putInt("position", position)

            intent.putExtras(bundle)    // intent 객체에 Bundle을 저장

            startActivityForResult(intent, 0)       // 화면전환
        }

        delete_button.setOnClickListener{

            val bookDataList : ArrayList<PhoneBookData>? = BookDataList.getInstance()
            bookDataList?.removeAt(position)
            setResult(DELETE_CODE)
            // 액티비티 종료
            finish();
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 1) {
            this.window.decorView.let {
                Snackbar.make(it as View, "이름과 번호를 정확히 입력해주세요!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }
        val bookDataList : ArrayList<PhoneBookData>? = BookDataList.getInstance()
        text_name.text = bookDataList?.get(position)!!.name
        text_number.text = bookDataList?.get(position).number
    }
}