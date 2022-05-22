package com.jarrod.memorygame.prefs

import android.content.Context

class Prefs(val context: Context) {
    val SHARED_PREFERENCES = "prefs"

    val SHARED_LIST = "list"
    val SHARED_DIFFICULT = "difficult"



    val storage = context.getSharedPreferences(SHARED_PREFERENCES, 0)

    fun saveTypeList(typeList:String){
        storage.edit().putString(SHARED_LIST, typeList).apply()
    }

    fun getTypeList(): String{
        return storage.getString(SHARED_LIST, "")!!
    }

    fun saveDifficut(dificult:Int){
        storage.edit().putInt(SHARED_DIFFICULT, dificult).apply()
    }

    fun getDifficut(): Int {
        return storage.getInt(SHARED_DIFFICULT, 0)
    }
}