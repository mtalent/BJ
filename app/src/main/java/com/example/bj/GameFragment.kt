package com.example.bj

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.bj.databinding.FragmentGameBinding
import com.example.bj.model.Ace
import com.example.bj.model.Card
import com.example.bj.model.Deck
import com.example.bj.model.Hand
import kotlin.random.Random


class GameFragment : Fragment() {
    private var rnd : Int = 0
    private var dScore : Int = 0
    private var pScore : Int = 0
    private lateinit var deck : Deck
    private lateinit var binding : FragmentGameBinding
    private lateinit var views: MutableList<View>
    private lateinit var btnHit : Button
    private lateinit var btnStay : Button
    private lateinit var tvPlayer : TextView
    private lateinit var tvDealer : TextView
    private lateinit var tvPlayerInt : TextView
    private lateinit var tvDealerInt : TextView
    private val playerHand : Hand = Hand()
    private val dealerHand : Hand = Hand()
    private var clickCount : Int = 13
    private var dClickCount : Int = 2
    private var buttonState : ButtonState = ButtonState.DEAL

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnHit = binding.btnHit
        btnStay = binding.btnStay
        tvDealer = binding.tvDScore
        tvDealerInt = binding.tvDScoreInt
        tvPlayer = binding.tvPScore
        tvPlayerInt = binding.tvPScoreInt
        tvDealerInt.text = dScore.toString()
        tvPlayerInt.text = pScore.toString()

        views = mutableListOf()
        views.add(binding.dealerCardOne)
        views.add(binding.dealerCardTwo)
        views.add(binding.dealerCardThree)
        views.add(binding.dealerCardFour)
        views.add(binding.dealerCardFive)
        views.add(binding.dealerCardSix)
        views.add(binding.dealerCardSeven)
        views.add(binding.dealerCardEight)
        views.add(binding.dealerCardNine)
        views.add(binding.dealerCardTen)
        views.add(binding.dealerCardEleven)
        views.add(binding.playerCardOne)
        views.add(binding.playerCardTwo)
        views.add(binding.playerCardThree)
        views.add(binding.playerCardFour)
        views.add(binding.playerCardFive)
        views.add(binding.playerCardSix)
        views.add(binding.playerCardSeven)
        views.add(binding.playerCardEight)
        views.add(binding.playerCardNine)
        views.add(binding.playerCardTen)
        views.add(binding.playerCardEleven)


        btnHit.setOnClickListener {
            if (buttonState == ButtonState.DEAL) {
                dealCards()
                clickCount = 13
                dClickCount = 2
                buttonState = ButtonState.HIT
                btnHit.text = getString(R.string.btnHit)
                btnStay.visibility = View.VISIBLE
                btnStay.isEnabled = true
            } else {
                rnd = rand((deck.deck.size) - 1)
                println("**********random number*************** $rnd")
                val selectedCard = getCard(rnd)
                views[clickCount].setBackgroundResource(selectedCard.imageResourceId)
                views[clickCount].visibility = View.VISIBLE
                playerHand.hand.add(selectedCard)
                val total : Int = getHandValue(playerHand)
                clickCount ++
                println("***********player hand value ************** ${getHandValue(playerHand)}")
                if (total > 21) {


                    Toast.makeText(activity, "PLAYER BUST WITH ${getHandValue(playerHand)}", Toast.LENGTH_SHORT).show()
                    dealerScore()
                    buttonState = ButtonState.DEAL
                    btnHit.text = getString((R.string.btnDeal))
                    btnStay.visibility = View.INVISIBLE
                    btnStay.isEnabled = false

                }
            }
        }

        btnStay.setOnClickListener{
            // val total : Int = dealerPlay()
            while (dealerPlay() < 17)  {
                val selectedCard =  getCard(rand((deck.deck.size) - 1))
                views[dClickCount].setBackgroundResource(selectedCard.imageResourceId)
                views[dClickCount].visibility = View.VISIBLE
                dealerHand.hand.add(selectedCard)
                dClickCount ++
                println("***********dealer hand value ************** ${getHandValue(dealerHand)}")
            }
            do {
                if (getHandValue(dealerHand) == 17) {
                    switchAnAce(dealerHand)
                }
            } while (getHandValue(dealerHand) == 17 && getNumAces(dealerHand) > 0)

            if (getHandValue(dealerHand) > 21) {
                Toast.makeText(activity, "DEALER BUSTED ${getHandValue(dealerHand)}", Toast.LENGTH_SHORT).show()
                playerScore()
            }
            else if (getHandValue(dealerHand) > getHandValue(playerHand)){
                Toast.makeText(activity, "DEALER WINS ${getHandValue(dealerHand)}", Toast.LENGTH_SHORT).show()

                dealerScore()
            }
            else if (getHandValue(dealerHand) == getHandValue(playerHand)){
                Toast.makeText(activity, "PUSH dealer ${getHandValue(dealerHand)} player ${getHandValue(playerHand)}", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(activity, "PLAYER WINS ${getHandValue(playerHand)}", Toast.LENGTH_SHORT).show()
                playerScore()
            }

            buttonState = ButtonState.DEAL
            btnHit.text = getString((R.string.btnDeal))
            btnStay.visibility = View.INVISIBLE
            btnStay.isEnabled = false
        }



    }


    private fun playerScore() {
        pScore ++
        tvPlayerInt.text = pScore.toString()
    }

    private fun dealerScore() {
        dScore ++
        tvDealerInt.text = dScore.toString()
    }

    private fun dealerPlay(): Int {
        flipHoldCard()
        return getHandValue(dealerHand)
    }

    private fun flipHoldCard() {
        views[1].setBackgroundResource(dealerHand.hand[1].imageResourceId)
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


    private fun dealCards() {
        //listViews()
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
        println("********playerHand*****value************** ${getHandValue(playerHand)}")
        println("*****dealerHand******value************** ${getHandValue(dealerHand)}")
    }

    private fun getHandValue(hand: Hand) : Int {
        return if (countNumbers(hand) <= 21) {
            countNumbers(hand)
        } else {
            handleAces(hand)
        }
    }

    private fun countNumbers(hand: Hand) : Int {
        var total = 0
        for (card in hand.hand) {
            if (card.number > 10) card.number = 10
            if (card.ace == Ace.ELEVEN) card.number = 11
            if (card.ace == Ace.ONE) card.number = 1
            total += card.number
        }
        return total
    }

    private fun  handleAces(hand: Hand): Int {
        if (getNumAces(hand) > 0) {
            switchAnAce(hand)
        }
        return countNumbers(hand)
    }

    private fun  getNumAces(hand: Hand): Int {
        var aces  = 0
        for (card in hand.hand) {
            if (card.ace == Ace.ELEVEN) {
                aces++
            }
        }
        return aces
    }

    private fun switchAnAce(hand: Hand) {
        val list : MutableList<Card> = mutableListOf()
        for (card in hand.hand) {
            if (card.ace == Ace.ELEVEN) {
                list.add(card)
            }
        }

        if (list.isNotEmpty()) {
            list[0].ace = Ace.ONE
        }
    }





}

enum class ButtonState {
    HIT,
    DEAL
}
