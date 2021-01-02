package com.example.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_item.*

class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        // 여러 인텐트를 구분하기 위한 Request code
        val DELETE_CODE : Int = 2

        // AddActivity의 intent를 받아 저장.
        val intent = getIntent()
        val name = intent.getStringExtra("name")
        val number = intent.getStringExtra("number")
        val position = intent.getIntExtra("position", 0)

        text_name.text = name
        text_number.text = number


        edit_button.setOnClickListener{
            // 화면전환 (intent 객체 생성), Edit
            val intent = Intent(this, EditActivity::class.java)

            // bundle 객체 생성, contents 저장
            val bundle = Bundle()
            bundle.putString("name", name)
            bundle.putString("number", number)

            intent.putExtras(bundle)    // intent 객체에 Bundle을 저장

            startActivity(intent)       // 화면전환
        }

        delete_button.setOnClickListener{
            // 화면전환 (intent 객체 생성), Main
            val intent = Intent(this, MainActivity::class.java)

            // bundle 객체 생성, contents 저장
            val bundle = Bundle()
            bundle.putInt("position", position)
            bundle.putInt("type", DELETE_CODE)

            intent.putExtras(bundle)    // intent 객체에 Bundle을 저장

            startActivity(intent)       // 화면전환
        }

    }
}