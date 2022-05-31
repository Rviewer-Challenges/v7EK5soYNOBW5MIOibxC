package com.jarrod.memorygame.data.controller

import com.jarrod.memorygame.data.source.Hiragana
import com.jarrod.memorygame.data.source.Temp
import com.jarrod.memorygame.models.Cards
import com.jarrod.memorygame.prefs.UserApplication.Companion.prefs

class ControllerImp: Controller {
    override suspend fun getAllCardsHiragana(): List<Cards> {
        return Temp.gameCards
    }

    override suspend fun getMoves(): String {
        return "Moves: ${prefs.getMoves()}"
    }

    override suspend fun getCardsLeft(): String {
        return "Left: ${prefs.getCardsLeft()}"
    }
}