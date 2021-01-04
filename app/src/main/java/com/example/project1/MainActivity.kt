package com.example.project1

import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastTimeBackPressed : Long = -1;
    val mAdapter = ViewPagerAdapter(this)

    //request code
    val FRAG1_CODE : Int = 0
    val FRAG2_CODE : Int = 1
    val FRAG3_CODE : Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 뷰페이저 설정
        val fragmentList = listOf(Fragment1(), Fragment2(), Fragment3())
        mAdapter.fragments = fragmentList

        //뷰페이저와 어댑터 연결
        view_pager.adapter = mAdapter

        // 탭레이아웃 관리
        TabLayoutMediator(tabs, view_pager) { tab: TabLayout.Tab, position: Int ->
            when (position) {
                0 -> {
                    tab.text = "Contact"
                    tab.setIcon(R.drawable.contactbook)
                }
                1 -> {
                    tab.text = "Gallery"
                    tab.setIcon(R.drawable.gallery)
                }

                2 -> {
                    tab.text = "Temp"
                    tab.setIcon(R.drawable.checkmark)
                }
                else -> {
                    tab.text = "Phone Book"
                    tab.setIcon(R.drawable.contactbook)
                }
            }
            view_pager.setCurrentItem(0)
        }.attach()
    }

    override fun onBackPressed() {
        if(System.currentTimeMillis() - lastTimeBackPressed < 2000){
            finish()
            return
        }
        Snackbar.make(view_pager, "뒤로가기 버튼을 한번 더 눌러 종료", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show()
        lastTimeBackPressed = System.currentTimeMillis();
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            // From Fragment1
            0-> {
                if (resultCode == 1) {
                    view_pager.let {
                        Snackbar.make(it, "이름과 번호를 정확히 입력해주세요!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    }
                }
                mAdapter.fragments[0].onActivityResult(requestCode, resultCode, data)
            }
            1-> {
                mAdapter.fragments[1].onActivityResult(requestCode, resultCode, data)
            }
            2-> {
                mAdapter.fragments[2].onActivityResult(requestCode, resultCode, data)
            }
        }

        view_pager.adapter = mAdapter
    }
}