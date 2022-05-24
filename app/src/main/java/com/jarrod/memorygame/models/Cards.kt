package com.jarrod.memorygame.models

class Cards{
    var isFlipped: Int = 0
    var srcImg:String = ""
    var nameImg:String = ""
    var nameImgKana:String = ""
    var kana: String = ""

    constructor( srcImg: String, nameImgKana:String, nameImg:String, kana: String, isFlipped: Int):this() {
        this.srcImg = srcImg
        this.nameImg = nameImg
        this.kana = kana
        this.nameImgKana = nameImgKana
        this.isFlipped = isFlipped
    }

    constructor()


}