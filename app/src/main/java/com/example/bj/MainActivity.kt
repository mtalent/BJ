package com.example.bj

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bj.model.Ace
import com.example.bj.model.Card
import com.example.bj.model.Deck
import com.example.bj.model.Hand
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private var rnd : Int = 0

    private lateinit var btnHit : Button
    private lateinit var btnStay : Button
    private val playerHand : Hand = Hand()
    private val dealerHand : Hand = Hand()
    private var clickCount : Int = 13
    private lateinit var deck : Deck
    private var cardsInDeck : Int = 51
    private lateinit var views: MutableList<View>
    private var buttonState : ButtonState = ButtonState.DEAL



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //imageView = findViewById<ImageView>(R.id.dealer_card_one)
        btnHit = findViewById(R.id.btn_hit)
        btnStay = findViewById(R.id.btn_stay)


        btnHit.setOnClickListener {

            if (buttonState == ButtonState.DEAL) {
                dealCards()
                clickCount = 13
                buttonState = ButtonState.HIT
                btnHit.text = getString(R.string.btnHit)
            } else {
                rnd = rand((deck.deck.size) - 1)
                println("**********random number*************** $rnd")
                val selectedCard = getCard(rnd)
                views[clickCount].setBackgroundResource(selectedCard.imageResourceId)
                views[clickCount].visibility = View.VISIBLE
                playerHand.hand.add(selectedCard)
                val total : Int = getHandValue()
                clickCount ++
                println("*********** hand value ************** ${getHandValue()}")
                if (total > 21) {
                    Toast.makeText(this, "you busted", Toast.LENGTH_SHORT).show()
                    buttonState = ButtonState.DEAL
                    btnHit.text = getString((R.string.btnDeal))

                }

            }


        }

        btnStay.setOnClickListener{
            
        }

    }










    private fun getCard(choose : Int) : Card {

        println("*********** choose  *********************$choose")

        val selectedCard = deck.deck[choose]
        deck.deck.removeAt(choose)

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
        playerHand.hand.clear()
        dealerHand.hand.clear()
        btnStay.visibility = View.VISIBLE
        for (view in views) {
            view.setBackgroundResource(0)
            view.visibility = View.INVISIBLE
        }



        for (i in 0..3) {

            when (i) {

                0 -> {
                    rnd = rand((deck.deck.size) - 1)
                    val selectedCard = getCard(rnd)
                    views[11].setBackgroundResource(selectedCard.imageResourceId)
                    views[11].visibility = View.VISIBLE
                    playerHand.hand.add(selectedCard)

                }

                1 -> {
                    rnd = rand((deck.deck.size) - 1)
                    val selectedCard = getCard(rnd)
                    views[0].setBackgroundResource(selectedCard.imageResourceId)
                    views[0].visibility = View.VISIBLE
                    dealerHand.hand.add(selectedCard)
                }

                2 -> {
                    rnd = rand((deck.deck.size) - 1)
                    val selectedCard = getCard(rnd)
                    views[12].setBackgroundResource(selectedCard.imageResourceId)
                    views[12].visibility = View.VISIBLE
                    playerHand.hand.add(selectedCard)

                }

                3 -> {
                    rnd = rand((deck.deck.size) - 1)
                    val selectedCard = getCard(rnd)
                    views[1].setBackgroundResource(R.drawable.backcard)
                    views[1].visibility = View.VISIBLE
                    dealerHand.hand.add(selectedCard)

                }
            }
        }

        println("*********** hand value ************** ${getHandValue()}")
    }

    private fun getHandValue() : Int {
        return if (countNumbers() <= 21) {
            countNumbers()
        } else {
            handleAces()
        }
    }

    private fun countNumbers() : Int {
        var total = 0
        for (card in playerHand.hand) {
            if (card.number > 10) card.number = 10
            if (card.ace == Ace.ELEVEN) card.number = 11
            if (card.ace == Ace.ONE) card.number = 1
            total += card.number
        }
        return total
    }

    private fun  handleAces(): Int {
      if (getNumAces() > 0) {
          switchAnAce()
      }
       return countNumbers()
    }

    private fun  getNumAces(): Int {

        var aces  = 0

        for (card in playerHand.hand) {
            if (card.ace == Ace.ELEVEN) {
                aces++
            }
        }
        return aces
    }







    private fun switchAnAce() {
        val list : MutableList<Card> = mutableListOf()
        for (card in playerHand.hand) {
            if (card.ace == Ace.ELEVEN) {
                list.add(card)
            }
        }

        if (list.isNotEmpty()) {
            list[0].ace = Ace.ONE
        }
    }
}



public enum class ButtonState {
    HIT,
    DEAL
}



