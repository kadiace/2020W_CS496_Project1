package com.example.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        add_button.setOnClickListener{
            // 화면전환 (intent 객체 생성)
            val intent = Intent(this, MainActivity::class.java)

            // 입력된 데이터 받기
            val name = edit_name.text.toString()
            val number = edit_number.text.toString()

            // Bundle을 통해서 전달
            val bundle = Bundle()
            bundle.putString("name", name)
            bundle.putString("number", number)

            intent.putExtras(bundle)    // intent 객체에 Bundle을 저장

            startActivity(intent)
        }
    }
}