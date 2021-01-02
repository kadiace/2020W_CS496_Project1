package com.example.project1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.phonebook.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // AddActivity의 intent를 받아 저장, 받을 fragment 정의
        val intent = getIntent()
        val fragment1 = Fragment1()


        val type = intent.getIntExtra("type", -1)

        when(type){
            0-> {
                val name = intent.getStringExtra("name")
                val number = intent.getStringExtra("number")

                // bundle 객체 생성, contents 저장
                val bundle = Bundle()
                bundle.putString("name", name)
                bundle.putString("number", number)
                bundle.putInt("type", type)

                // fragment1로 번들 전달
                fragment1.arguments = bundle
            }
            1-> {
                val name = intent.getStringExtra("name")
                val number = intent.getStringExtra("number")
                val position = intent.getIntExtra("position", 0)

                // bundle 객체 생성, contents 저장
                val bundle = Bundle()
                bundle.putString("name", name)
                bundle.putString("number", number)
                bundle.putInt("position", position)
                bundle.putInt("type", type)

                // fragment1로 번들 전달
                fragment1.arguments = bundle
            }
            2-> {
                val position = intent.getIntExtra("position", 0)

                // bundle 객체 생성, contents 저장
                val bundle = Bundle()
                bundle.putInt("position", position)
                bundle.putInt("type", type)

                // fragment1로 번들 전달
                fragment1.arguments = bundle
            }
        }

        // 뷰페이저 설정
        val fragmentList = listOf(fragment1, Fragment2(), Fragment3())
        val adapter = ViewPagerAdapter(this)
        adapter.fragments = fragmentList

        //뷰페이저와 어댑터 연결
        view_pager.adapter = adapter

        // 탭레이아웃 관리
        TabLayoutMediator(tabs, view_pager) { tab: TabLayout.Tab, position: Int ->
            when (position) {
                0 -> tab.text = "Phone Book"
                1 -> tab.text = "Gallery"
                2 -> tab.text = "Temp"
                else -> tab.text = "Phone Book"
            }
            view_pager.setCurrentItem(0)
        }.attach()
    }
}