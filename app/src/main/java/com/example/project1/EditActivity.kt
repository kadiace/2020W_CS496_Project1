package com.example.project1

import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
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
//            //기존의 main, item activity 제거
//            MainActivity().act.finish()
//            ItemActivity().act.finish()

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

            // 액티비티 종료
            finish();
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val focusView: View? = currentFocus
        if (focusView != null) {
            val rect = Rect()
            focusView.getGlobalVisibleRect(rect)
            val x = ev.x.toInt()
            val y = ev.y.toInt()
            if (!rect.contains(x, y)) {
                val imm: InputMethodManager =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0)
                focusView.clearFocus()
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}