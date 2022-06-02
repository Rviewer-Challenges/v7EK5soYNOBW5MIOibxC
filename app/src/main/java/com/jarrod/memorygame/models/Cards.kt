package com.jarrod.memorygame.models

import android.graphics.drawable.Drawable

class Cards{
    var isFlipped: Int = 0
    var srcImg:Int = 0
    var nameImg:String = ""
    var nameImgKana:String = ""
    var kana: String = ""

    constructor( srcImg: Int, nameImgKana:String, nameImg:String, kana: String, isFlipped: Int):this() {
        this.srcImg = srcImg
        this.nameImg = nameImg
        this.kana = kana
        this.nameImgKana = nameImgKana
        this.isFlipped = isFlipped
    }

    constructor()


}