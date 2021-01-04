package com.example.project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class GameActivity2 : AppCompatActivity() {

    lateinit var animalList : ArrayList<String>
    lateinit var jobList : ArrayList<String>
    lateinit var movieList : ArrayList<String>
    lateinit var realuseList : ArrayList<String>
    lateinit var worldBestList : ArrayList<String>
    lateinit var greatList : ArrayList<String>
    lateinit var foodList : ArrayList<String>
    lateinit var sportsList : ArrayList<String>
    lateinit var celebList : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game2)

        // Set answer list
        animalList = arrayListOf("사자", "호랑이", "판다", "나무늘보", "코뿔소", "고슴도치", "곰", "하마")
        jobList = arrayListOf("개발자", "소방관", "바리스타", "PD")
        movieList = arrayListOf("라라랜드", "인셉션", "어벤져스", "놈놈놈")
        realuseList = arrayListOf("와이파이", "단톡방", "핸드폰", "텀블러")
        worldBestList = arrayListOf("신정윤", "김현아", "이정인", "한다진", "함창수")
        greatList = arrayListOf("이순신", "세종대왕", "장영실")
        foodList = arrayListOf("짜장면", "초밥", "순댓국", "냉면")
        sportsList = arrayListOf("탁구", "클라이밍", "야구", "스키점프")
        celebList = arrayListOf("용이형")





    }
}