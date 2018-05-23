package com.example.root.absolution2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast




class DataBase(context: Context) : SQLiteOpenHelper(context,"AbsolutionDB",null,1){

    val primary = "PRIMARY KEY"
    val charTable = "characters"
    val charId = "id"
    val charIdType = "INTEGER"
    val charName =  "name"
    val charNameType ="TEXT"


    override fun onCreate(db: SQLiteDatabase?) {

        println("TABLE CREATION")
        try {

            db!!.execSQL("CREATE TABLE $charTable ($charId $charId $primary, $charName $charNameType)");

        }catch(err: Exception){
            println("ERR Sqlite onCreate " + err.toString())
        }
        db!!.close()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {


    }

    fun createCharacter(newChar : NewChar){

        val db = writable();
        val cv = ContentValues();
        cv.put(charName,newChar.name);

        val result = db.insert(charTable,null,cv)



        if(result == -1L){
            println("ERRROR inserting result")
        }else{
            println(result)
        }
        db.close()
    }

    fun dellChar(name: String){

        val db = writable()
        println(db.execSQL("DELETE FROM $charTable WHERE $charName=\"$name\""))
        db.close()
    }


    fun getCharacters() : ArrayList<NewChar>{

        val res = ArrayList<NewChar>();
        val db = writable()

        val result = db.rawQuery("SELECT * FROM $charTable",null)

        if(result.moveToNext() == false){
            db.close();
            return res;
        }

        while(result.moveToNext()){
            res.add(NewChar(result.getInt(0),result.getString(1)))
        }

        db.close()
        return res;
    }

    fun getCharacter(id: Integer) : NewChar{


        val db = writable()

        val result = db.rawQuery("SELECT * FROM $charTable WHERE id=$id",null)




        result.moveToNext()

        db.close()
        println(result.getString(1))

        return NewChar(result.getInt(0),result.getString(1));




    }




    //Dev functions

    fun DestroyTable(){
        println("DROP TABLE RESULTS" + writable().execSQL("DROP TABLE $charTable"));

    }


    private fun writable() : SQLiteDatabase{
        return this.writableDatabase;
    }

}

