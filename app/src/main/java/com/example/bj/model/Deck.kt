package com.example.bj.model

import com.example.bj.R


class Deck   {

    private val value = mapOf(1 to "ace", 2 to "two", 3 to "three", 4 to "four", 5 to "five", 6 to "six", 7 to "seven", 8 to "eight", 9 to "nine",
        10 to "ten", 11 to "jack", 12 to "queen", 13 to "king")

    private val suit = mapOf(0 to "hts", 1 to "clb", 2 to "spd", 3 to "dia")

    private val picture = mapOf("ace_hts" to R.drawable.ace_hts, "ace_clb" to R.drawable.ace_clb, "ace_dia" to R.drawable.ace_dia, "ace_spd" to R.drawable.ace_spd,
        "two_hts" to R.drawable.two_hts, "two_clb" to R.drawable.two_clb, "two_dia" to R.drawable.two_dia, "two_spd" to R.drawable.two_spd,
        "three_hts" to R.drawable.three_hts, "three_clb" to R.drawable.three_clb, "three_dia" to R.drawable.three_dia, "three_spd" to R.drawable.three_spd,
        "four_hts" to R.drawable.four_hts, "four_clb" to R.drawable.four_clb, "four_dia" to R.drawable.four_dia, "four_spd" to R.drawable.four_spd,
        "five_hts" to R.drawable.five_hts, "five_clb" to R.drawable.five_clb, "five_dia" to R.drawable.five_dia, "five_spd" to R.drawable.five_spd,
        "six_hts" to R.drawable.six_hts, "six_clb" to R.drawable.six_clb, "six_dia" to R.drawable.six_dia, "six_spd" to R.drawable.six_spd,
        "seven_hts" to R.drawable.seven_hts, "seven_clb" to R.drawable.seven_clb, "seven_dia" to R.drawable.seven_dia, "seven_spd" to R.drawable.seven_spd,
        "eight_hts" to R.drawable.eight_hts, "eight_clb" to R.drawable.eight_clb, "eight_dia" to R.drawable.eight_dia, "eight_spd" to R.drawable.eight_spd,
        "nine_hts" to R.drawable.nine_hts, "nine_clb" to R.drawable.nine_clb, "nine_dia" to R.drawable.nine_dia, "nine_spd" to R.drawable.nine_spd,
        "ten_hts" to R.drawable.ten_hts, "ten_clb" to R.drawable.ten_clb, "ten_dia" to R.drawable.ten_dia, "ten_spd" to R.drawable.ten_spd,
        "jack_hts" to R.drawable.jack_hts, "jack_clb" to R.drawable.jack_clb, "jack_dia" to R.drawable.jack_dia, "jack_spd" to R.drawable.jack_spd,
        "queen_hts" to R.drawable.queen_hts, "queen_clb" to R.drawable.queen_clb, "queen_dia" to R.drawable.queen_dia, "queen_spd" to R.drawable.queen_spd,
        "king_hts" to R.drawable.king_hts, "king_clb" to R.drawable.king_clb, "king_dia" to R.drawable.king_dia, "king_spd" to R.drawable.king_spd, "back_card" to R.drawable.backcard)

    val deck : MutableList<Card> = createDeck()

    private fun createDeck(): MutableList<Card> {
        val temp:MutableList<Card> = ArrayList()
            for (s in 0..3)  {
                for(v in 1..13) {
                    val crdPicture: String = value.getValue(v) + "_" + suit.getValue(s)
                    val card = Card(picture[crdPicture]!!, value.getValue(v), suit.getValue(s), v, Ace.NOT_ACE)
                    if (card.value.contains("ace")){
                        card.ace = Ace.ELEVEN
                        card.number = 11
                    }
                    temp.add(card)
                }
            }

        return temp
    }


}

