package com.jarrod.memorygame.data.controller

import com.jarrod.memorygame.models.Cards
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository(val network : Controller) {
    fun getAllCardsHiragana(): Flow<List<Cards>> {
        return flow {
            emit(network.getAllCardsHiragana())
        }
    }
}