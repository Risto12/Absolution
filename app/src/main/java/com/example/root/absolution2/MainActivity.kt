package com.example.root.absolution2

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.character.view.*


class MainActivity : AppCompatActivity() {


    var database = DataBase(this);

    //starts activity_char
    fun startCharAct(id: Int){
        val intent = Intent(this, CharActivity::class.java)
        intent.putExtra("id",id)
        startActivity(intent)
    }

    fun loadChars(){

        val list = ArrayList<NewChar>();

        database.getCharacters().forEach { list.add(NewChar(it.id,it.name,it.type,it.lvl,it.hp,it.mind,it.skill,it.pic,it.gold)) }

        playerList.adapter = CharAdapter(list, this,this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

       //this.deleteDatabase("AbsolutionDB");

        loadChars()

        playerList.layoutManager = LinearLayoutManager(this)


        create.setOnClickListener {

            val intent = Intent(this, CharCreationActivity::class.java)

            startActivity(intent)

        }



    }


    override fun onRestart() {
        super.onRestart()
        loadChars()
    }

}

private class CharAdapter(val items : ArrayList<NewChar>, val context: Context,main: MainActivity) : RecyclerView.Adapter<ViewHolder>() {

   private val main = main;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var hold: ViewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.character, parent, false))

        return hold;

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.character?.text = items.get(position).name;
        // each item on map gets an listener
        holder?.character?.setOnClickListener {
            main.startCharAct(items.get(position).id)

        }
        holder?.character?.setOnLongClickListener {items.get(position).dev(); true}
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }


}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds characters
    val character = view.character;



}


/* dev
    override fun onResume() {
        super.onResume()
        println("Onresume")
    }

    override fun onStart() {
        super.onStart()
        println("OnStart")
    }

    override fun onStop() {
        super.onStop()
        println("OnSttop")
    }
*/