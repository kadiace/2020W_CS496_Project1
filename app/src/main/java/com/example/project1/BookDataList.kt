package com.example.project1

import android.content.Context


class BookDataList private constructor() {
    companion object {
        @Volatile private var instance: ArrayList<PhoneBookData>? = null

        @JvmStatic fun getInstance(): ArrayList<PhoneBookData>? =
            instance ?: synchronized(this) {
                instance ?: ArrayList<PhoneBookData>().also {
                    instance = it
                }
            }
    }
}