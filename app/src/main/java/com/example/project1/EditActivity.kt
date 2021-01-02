package com.example.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        // EDIT REQUEST CODE
        val EDIT_CODE : Int = 1

        // get Intent
        val inIntent = getIntent()
        var name = inIntent.getStringExtra("name")
        var number = inIntent.getStringExtra("number")
        val position = inIntent.getIntExtra("position", 0)

        edit_name.setText(name)
        edit_number.setText(number)


        edit_button.setOnClickListener{
            // 화면전환 (intent 객체 생성)
            val outent = Intent(this, MainActivity::class.java)

            // 입력된 데이터 받기
            name = edit_name.text.toString()
            number = edit_number.text.toString()

            // Bundle을 통해서 전달
            val bundle = Bundle()
            bundle.putString("name", name)
            bundle.putString("number", number)
            bundle.putInt("position", position)
            bundle.putInt("type", EDIT_CODE)

            outent.putExtras(bundle)    // intent 객체에 Bundle을 저장

            startActivity(outent)
        }
    }
}