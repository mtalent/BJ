package com.example.bj

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.bj.model.Card
import com.example.bj.model.Deck
import kotlin.random.Random


class MainActivity : AppCompatActivity() {


    private lateinit var deck: Deck
    private lateinit var views: MutableList<View>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //imageView = findViewById<ImageView>(R.id.dealer_card_one)
        val btnHit: Button = findViewById<Button>(R.id.btn_hit)

        btnHit.setOnClickListener {
            listViews()
            val rnd = rand(1, 51)
             println("************************* $rnd")
            getCard(rnd)

        }
        //getCard()
    }

    private fun getCard(choose : Int)  {
        deck = Deck()
        val selectedCard = deck.deck[choose]
        println("********************************$selectedCard")
       views[0].setBackgroundResource(selectedCard.imageResourceId)
       views[0].visibility = View.VISIBLE


    }

    private fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        val rand = Random(System.nanoTime())
        return (start..end).random(rand)
    }

    private fun listViews() {
        views = mutableListOf()
        views.add(findViewById(R.id.dealer_card_one))
        views.add(findViewById(R.id.dealer_card_two))
        views.add(findViewById(R.id.dealer_card_three))
        views.add(findViewById(R.id.dealer_card_four))
        views.add(findViewById(R.id.dealer_card_five))
        views.add(findViewById(R.id.dealer_card_six))
        views.add(findViewById(R.id.dealer_card_seven))
        views.add(findViewById(R.id.dealer_card_eight))
        views.add(findViewById(R.id.dealer_card_nine))
        views.add(findViewById(R.id.dealer_card_ten))
        views.add(findViewById(R.id.dealer_card_eleven))
        views.add(findViewById(R.id.player_card_two))
        views.add(findViewById(R.id.player_card_three))
        views.add(findViewById(R.id.player_card_four))
        views.add(findViewById(R.id.player_card_five))
        views.add(findViewById(R.id.player_card_six))
        views.add(findViewById(R.id.player_card_seven))
        views.add(findViewById(R.id.player_card_eight))
        views.add(findViewById(R.id.player_card_nine))
        views.add(findViewById(R.id.player_card_ten))
        views.add(findViewById(R.id.player_card_eleven))
    }
}