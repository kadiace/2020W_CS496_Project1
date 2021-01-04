package com.example.project1

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_lier.*
import java.util.*
import kotlin.collections.ArrayList

class LierActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lier)

        // get Intent
        val inIntent = getIntent()
        val answer = inIntent.getStringExtra("answer").toString()
        val number = inIntent.getIntExtra("number", 0)
        val lier = inIntent.getIntExtra("lier", 0)

        // Set initial value
        var count : Int = 0
        var result : ArrayList<Int> = arrayListOf()

        // make list
        var list = mutableListOf<String>()
        for (i in 0..lier-1) {
            list.add("라이어")
        }
        for (i in lier..number-1) {
            list.add(answer)
        }
        list.shuffle()

        answer_shit.setOnClickListener{
            if (count < list.size*2-1) {
                if (count % 2 == 0) {
                    text_answer.text = list.get(count / 2)
                    if (list.get(count / 2).equals("라이어")) {
                        result.add(count/2 + 1)
                        text_answer.setTextColor(Color.parseColor("#C00000"))
                    }
                } else {
                    text_answer.setTextColor(Color.parseColor("#747474"))
                    text_answer.text = "과연..."
                }
            }
            else if (count == list.size*2-1) {
                text_answer.setTextColor(Color.parseColor("#747474"))
                text_answer.text = "라이어는 누굴까? 두구두구"
            }
            else if (count == list.size*2) {
                var resultText : String? = "${result.get(0)}"
                for (i in 1..result.size-1) {
                    resultText = "$resultText, ${result.get(i)}"
                }
                resultText = "$resultText 번"
                text_answer.text = resultText
                text_answer.setTextColor(Color.parseColor("#C00000"))
            }
            else if (count == list.size*2+1) {
                text_answer.setTextColor(Color.parseColor("#747474"))
                text_answer.text = "다시 눌러 뒤로가기"
            }
            else {
                finish()
            }
            count++
        }
    }
}