package com.jarrod.memorygame.prefs

import android.content.Context

class Prefs(val context: Context) {
    val SHARED_PREFERENCES = "prefs"

    val SHARED_LIST = "list"
    val SHARED_DIFFICULT_COLUMNS = "difficult_columns"
    val SHARED_DIFFICULT = "difficult"
    val SHARED_MOVES = "move"
    val SHARED_TIME = "time"
    val SHARED_CARDSLEFT = "cardsleft"

    val storage = context.getSharedPreferences(SHARED_PREFERENCES, 0)

    fun saveTypeList(typeList:String){
        storage.edit().putString(SHARED_LIST, typeList).apply()
    }

    fun getTypeList(): String{
        return storage.getString(SHARED_LIST, "")!!
    }

    fun saveDifficultColumns(dificult:Int){
        storage.edit().putInt(SHARED_DIFFICULT_COLUMNS, dificult).apply()
    }

    fun getDifficultColumns(): Int {
        return storage.getInt(SHARED_DIFFICULT_COLUMNS, 0)
    }

    fun saveDifficult(typeList:String){
        storage.edit().putString(SHARED_DIFFICULT, typeList).apply()
    }

    fun getDifficult(): String{
        return storage.getString(SHARED_DIFFICULT, "")!!
    }

    fun saveMoves(moves:Int){
        storage.edit().putInt(SHARED_MOVES, moves).apply()
    }

    fun getMoves(): Int {
        return storage.getInt(SHARED_MOVES, 0)
    }

    fun saveTime(time:String){
        storage.edit().putString(SHARED_TIME, time).apply()
    }

    fun getTime(): String{
        return storage.getString(SHARED_TIME, "")!!
    }

    fun saveCardsLeft(cardsLeft:Int){
        storage.edit().putInt(SHARED_CARDSLEFT, cardsLeft).apply()
    }

    fun getCardsLeft(): Int {
        return storage.getInt(SHARED_CARDSLEFT, 0)
    }
}