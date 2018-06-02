package com.example.root.absolution2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import android.graphics.Bitmap
import android.R.attr.bitmap
import java.io.ByteArrayOutputStream
import android.graphics.BitmapFactory
import java.sql.Blob


class DataBase(context: Context) : SQLiteOpenHelper(context,"AbsolutionDB",null,1){



    val primary = "PRIMARY KEY"
    val itemTable = "item" // not yet implemented
    val charTable = "character"
    val charId = "id"
    val charIdType = "INTEGER"
    val charName =  "name"
    val charNameType ="TEXT"
    val charTyp = "class"
    val charTypType = "TEXT"
    val charLvl = "lvl"
    val charLvlType = "INTEGER"
    val charHP = "hp"
    val charHPType = "INTEGER"
    val charMind = "mind"
    val charMindType = "INTEGER"
    val charSkill = "skill"
    val charSkillType = "INTEGER"
    val charPic = "pic"
    val charPicType ="BLOB"
    val charGold = "gold"
    val charGoldType ="INTEGER"

    //not yet implemented
    val bagItem = "item"
    val bagItemType = "TEXT"




    override fun onCreate(db: SQLiteDatabase?) {

        val sql : String = "CREATE TABLE $charTable ($charId $charIdType $primary,$charName $charNameType, $charTyp $charTypType, $charLvl $charLvlType, $charHP $charHPType,$charMind $charMindType,$charSkill $charSkillType,$charPic $charPicType,$charGold $charGoldType)"

        println(sql)
        try {

            db!!.execSQL(sql);

        }catch(err: Exception){
            println("ERR Sqlite onCreate " + err.toString())
        }finally {

        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {


    }

    fun createCharacter(newChar : NewChar){

        val db = this.writableDatabase


            val stream = ByteArrayOutputStream()
            var pic: ByteArray? = null;

        if(newChar.pic != null) {

            newChar.pic?.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            pic = stream.toByteArray()

        }

        val cv = ContentValues()

        cv.put(charName,newChar.name)
        cv.put(charTyp,newChar.type)
        cv.put(charLvl,newChar.lvl)
        cv.put(charHP,newChar.hp)
        cv.put(charMind,newChar.mind)
        cv.put(charSkill,newChar.skill)
        cv.put(charPic,pic)
        cv.put(charGold,newChar.gold)




        val result = db.insert(charTable,null,cv)

        if(result == -1L){
            println("ERRROR inserting result")
        }else{
            println(result)
        }
        db.close()
    }

    fun dellChar(id: Int){


        val db = this.writableDatabase
        db.execSQL("DELETE FROM $charTable WHERE $charId=$id")
        db.close()
    }


    fun getCharacters() : ArrayList<NewChar>{

        val res = ArrayList<NewChar>();
        val db = this.writableDatabase

        val result = db.rawQuery("SELECT * FROM $charTable",null)

        if(result.moveToNext() == false){
            db.close();
            return res;
        }




        res.add(NewChar(result.getInt(0),result.getString(1),result.getString(2),result.getInt(3),result.getInt(4),result.getInt(5),
                result.getInt(6), blop(result.getBlob(7)), result.getInt(8)))

        while(result.moveToNext()){

            res.add(NewChar(result.getInt(0),result.getString(1),result.getString(2),result.getInt(3),result.getInt(4),result.getInt(5),
                    result.getInt(6), blop(result.getBlob(7)), result.getInt(8)))
        }

        db.close()
        return res;
    }

    fun getCharacter(id: Int) : NewChar{


        val db = this.writableDatabase

        val result = db.rawQuery("SELECT * FROM $charTable WHERE id=$id",null)




        result.moveToNext()

        db.close()


        return NewChar(result.getInt(0),result.getString(1),result.getString(2),result.getInt(3),result.getInt(4),result.getInt(5),
                result.getInt(6), blop(result.getBlob(7)), result.getInt(8))


    }

    fun updateCharacter(newChar: NewChar){


        val db = this.writableDatabase

        val cv = ContentValues()



        cv.put(charLvl,newChar.lvl)
        cv.put(charHP,newChar.hp)
        cv.put(charMind,newChar.mind)
        cv.put(charSkill,newChar.skill)
        cv.put(charGold,newChar.gold)



        val result = db.update(charTable,cv,"id=${newChar.id}",null)

        if(result == -1){
            println("ERRROR inserting result")
        }else{
            println(result)
        }
        db.close()








    }




    //Dev functions

    fun DestroyTable(){
        println("DROP TABLE RESULTS" + this.writableDatabase.execSQL("DROP TABLE $charTable"));

    }

    private fun blop(blop: ByteArray?): Bitmap?{

        if(blop != null) {

            val bm = BitmapFactory.decodeByteArray(blop, 0, blop.size)

            return bm
        }else{
            return null
        }
    }


}

