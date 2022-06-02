package com.jarrod.memorygame.data.controller

import com.jarrod.memorygame.models.Cards

interface Controller {
    suspend fun getAllCardsHiragana(): List<Cards>
    suspend fun getAllCardsKatakana(): List<Cards>

    suspend fun getMoves(): String
    suspend fun getCardsLeft(): String

}