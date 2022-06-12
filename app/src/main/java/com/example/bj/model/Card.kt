package com.example.bj.model

import androidx.annotation.DrawableRes

data class Card(
   val imageResourceId: Int,
   val value: String,
   val suite: String,
   var number : Int,
   var ace : Ace
   )



enum class Ace {
   NOT_ACE,
   ONE,
   ELEVEN
}




