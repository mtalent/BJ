package com.example.bj

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bj.model.Card
import com.example.bj.model.Deck
import com.example.bj.model.Hand
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private var rnd : Int = 0
    private var cardsInDeck : Int = 52
    private lateinit var btnHit : Button
    private lateinit var btnStay : Button
    private val playerHand : Hand = Hand()
    private val dealerHand : Hand = Hand()
    private var clickCount : Int = 13
    private lateinit var deck: Deck
    private lateinit var views: MutableList<View>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //imageView = findViewById<ImageView>(R.id.dealer_card_one)
        btnHit = findViewById(R.id.btn_hit)
        btnStay = findViewById(R.id.btn_stay)
        dealCards()

        btnHit.setOnClickListener {
           rnd = rand(cardsInDeck)
            println("************************* $rnd")
            val selectedCard = getCard(rnd)
            views[clickCount].setBackgroundResource(selectedCard.imageResourceId)
            views[clickCount].visibility = View.VISIBLE
            playerHand.hand.add(selectedCard)
            evaluateHand()
            clickCount  ++
        }

        btnStay.setOnClickListener{

            dealCards()



        }
        //getCard()
    }

    private fun evaluateHand() {
        var handValue = 0
        for (card in playerHand.hand) {
            if (card.number > 10) card.number = 10
            handValue += card.number
            if (handValue > 21) {
                Toast.makeText(this, "you busted", Toast.LENGTH_LONG).show()
                playerHand.hand.clear()
                clickCount = 12


            }

        }
    }

    private fun getCard(choose : Int) : Card {

        val selectedCard = deck.deck[choose]
        deck.deck.removeAt(choose)
        cardsInDeck --
        println("********************************$selectedCard")
  //      views[clickCount].setBackgroundResource(selectedCard.imageResourceId)
     //   views[clickCount].visibility = View.VISIBLE
        return selectedCard


    }

    private fun rand(end: Int): Int {
        require(end > 0) { "Illegal Argument" }
        val rand = Random(System.nanoTime())
        return (0..end).random(rand)
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
        views.add(findViewById(R.id.player_card_one))
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

    private fun dealCards() {
        listViews()
        deck=Deck()
        for (view in views) {
            view.setBackgroundResource(0)
            view.visibility = View.INVISIBLE
        }



        for (i in 0..3) {

            when (i) {

                0 -> {
                    rnd = rand(cardsInDeck)
                    val selectedCard = getCard(rnd)
                    views[11].setBackgroundResource(selectedCard.imageResourceId)
                    views[11].visibility = View.VISIBLE
                    playerHand.hand.add(selectedCard)
                    cardsInDeck --
                }

                1 -> {
                    rnd = rand(cardsInDeck)
                    val selectedCard = getCard(rnd)
                    views[0].setBackgroundResource(selectedCard.imageResourceId)
                    views[0].visibility = View.VISIBLE
                    cardsInDeck --
                }

                2 -> {
                    rnd = rand(cardsInDeck)
                    val selectedCard = getCard(rnd)
                    views[12].setBackgroundResource(selectedCard.imageResourceId)
                    views[12].visibility = View.VISIBLE
                    playerHand.hand.add(selectedCard)
                    cardsInDeck --
                }

                3 -> {
                    rnd = rand(cardsInDeck)
                    val selectedCard = getCard(49)
                    views[1].setBackgroundResource(selectedCard.imageResourceId)
                    views[1].visibility = View.VISIBLE
                    cardsInDeck --
                }

            }


        }

    }
}



