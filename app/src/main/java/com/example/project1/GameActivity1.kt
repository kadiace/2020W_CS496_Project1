package com.example.project1

import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game1.*
import kotlinx.android.synthetic.main.activity_game1_list_item.view.*


class GameActivity1 : AppCompatActivity() {
    private var start_flag : Int = 0
    private val cardset_init: Map<String, Int> = mapOf(
        "A" to 4, "2" to 4, "3" to 4, "4" to 4,
        "5" to 4, "6" to 4, "7" to 4, "8" to 4,
        "8" to 4, "9" to 4, "10" to 4, "J" to 4,
        "Q" to 4, "K" to 4, "Joker" to 2
    )
    private val cardkeys_init = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "Joker")
    private var cardset = cardset_init
    private var cardkeys = cardkeys_init

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game1)

        card_A.card_text.text="A"
        card_2.card_text.text="2"
        card_3.card_text.text="3"
        card_4.card_text.text="4"
        card_5.card_text.text="5"
        card_6.card_text.text="6"
        card_7.card_text.text="7"
        card_8.card_text.text="8"
        card_9.card_text.text="9"
        card_10.card_text.text="10"
        card_J.card_text.text="J"
        card_Q.card_text.text="Q"
        card_K.card_text.text="K"
        card_Joker.card_text.text="Joker"

        start_text.bringToFront()
        card_back_view.layoutParams.height = 548
        card_back_view.layoutParams.width = 292

        remindcards.setOnClickListener{
            if(start_flag == 0){
                start_text.visibility = View.INVISIBLE
            }
            throwCard()
        }
        //scaleImage(card_back_view)
    }

    private fun throwCard(){
        while(true){
            var pick_card = cardkeys.shuffled().take(1)[0]
            if(cardset[pick_card] == 0){
                cardkeys.remove(pick_card)
            }
            else{
                cardset[pick_card]-=1
                return pick_card
            }
        }
    }

}