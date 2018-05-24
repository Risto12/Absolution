package com.example.root.absolution2

import android.graphics.Bitmap

class NewChar(var id: Int, var name: String, var type: String, var lvl: Int, var hp: Int, var mind: Int, var skill: Int, var pic: Bitmap?,
              var gold: Int){
//class puuttuu
//levle puuttuu
    val items :ArrayList<String> = ArrayList<String>()

    fun getPic() : Byte?{
        return null
    }

    fun dev(){
        println("""$id
            |$name
            |$type
            |$lvl
            |$hp
            |$mind
            |$skill
            |$gold""".trimMargin())
    }

}