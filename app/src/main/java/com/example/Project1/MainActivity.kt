package com.example.project1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 뷰페이저 설정
        val fragmentList = listOf(Fragment1(), Fragment2(), Fragment3())
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