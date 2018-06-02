package com.example.root.absolution2

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

class NewChar(var id: Int, var name: String, var type: String, var lvl: Int, var hp: Int, var mind: Int, var skill: Int, var pic: Bitmap?,
              var gold: Int){


    val items :ArrayList<String> = ArrayList<String>()

    fun getPic() : Byte?{
        return null
    }

    fun getDrawable() : Drawable? {

        if(pic != null) {

            val draw: Drawable = BitmapDrawable(pic)

            return draw

        }else{

            return null
        }

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