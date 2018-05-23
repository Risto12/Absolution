package com.example.root.absolution2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast



class DataBase(context: Context) : SQLiteOpenHelper(context,"AbsolutionDB",null,1){



    val primary = "PRIMARY KEY"
    val itemTable = "item"
    val charTable = "character"
    val charId = "id"
    val charIdType = "INTEGER"
    val charName =  "name"
    val charNameType ="TEXT"
    val charHP = "hp"
    val charHPType = "Integer"
    val charMind = "mind"
    val charMindType = "Integer"
    val charSkill = "skill"
    val charSkillType = "Integer"
    val charPic = "pic"
    val charPicType ="blob"
    val charGold = "gold"
    val charGoldType ="Integer"

    val bagItem = "item"
    val bagItemType = "Text"




    override fun onCreate(db: SQLiteDatabase?) {

        val sql : String = "CREATE TABLE $charTable ($charId $charIdType $primary,$charName $charNameType,$charHP $charHPType,$charMind $charMindType,$charSkill $charSkillType,$charPic $charPicType,$charGold $charGoldType)"

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

        val cv = ContentValues()

        cv.put(charName,newChar.name)
        cv.put(charHP,newChar.hp)
        cv.put(charMind,newChar.mind)
        cv.put(charSkill,newChar.skill)
        cv.put(charPic,1)
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
        println(db.execSQL("DELETE FROM $charTable WHERE $charId=$id"))
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

        while(result.moveToNext()){
            println(result.getInt(0))
            res.add(NewChar(result.getInt(0),result.getString(1),result.getInt(2),result.getInt(3),
                    result.getInt(4), null, result.getInt(6)))
        }

        db.close()
        return res;
    }

    fun getCharacter(id: Integer) : NewChar{


        val db = this.writableDatabase

        val result = db.rawQuery("SELECT * FROM $charTable WHERE id=$id",null)




        result.moveToNext()

        db.close()
        println(result.getString(1))

        return NewChar(result.getInt(0),result.getString(1),0,0,0,null,0);




    }




    //Dev functions

    fun DestroyTable(){
        println("DROP TABLE RESULTS" + this.writableDatabase.execSQL("DROP TABLE $charTable"));

    }




}

