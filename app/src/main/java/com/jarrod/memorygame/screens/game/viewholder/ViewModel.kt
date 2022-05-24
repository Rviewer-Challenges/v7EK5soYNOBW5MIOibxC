package com.jarrod.memorygame.screens.game.viewholder

import androidx.lifecycle.MutableLiveData
import com.jarrod.memorygame.data.controller.ControllerImp
import com.jarrod.memorygame.data.controller.Repository
import com.jarrod.memorygame.models.Cards
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import androidx.lifecycle.ViewModel
import com.jarrod.memorygame.prefs.UserApplication.Companion.prefs

class ViewModel: ViewModel(){

    val repository = Repository(ControllerImp())
    val cardsList = MutableLiveData<List<Cards>>()
    val moves = MutableLiveData<String>()


    fun updateCards() {
        repository.getAllCardsHiragana().onEach {
            cardsList.postValue(it)
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    fun updateMoves() {
        repository.getMoves().onEach {
            moves.postValue(it)
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }
}