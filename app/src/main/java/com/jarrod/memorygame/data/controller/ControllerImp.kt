package com.jarrod.memorygame.data.controller

import com.jarrod.memorygame.data.source.Hiragana
import com.jarrod.memorygame.models.Cards

class ControllerImp: Controller {
    override suspend fun getAllCardsHiragana(): List<Cards> {
        return Hiragana.hiraganaCards
    }
}