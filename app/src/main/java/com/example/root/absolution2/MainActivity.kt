package com.example.root.absolution2

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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

    val list = ArrayList<NewChar>();



    fun loadChars(){

        database.getCharacters().forEach { list.add(NewChar(it.id,it.name)) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //this.deleteDatabase("AbsolutionDB");
        //database.insertData(NewChar(1,"Petteri"))

        loadChars();

        playerList.layoutManager = LinearLayoutManager(this)

        playerList.adapter = CharAdapter(list, this)

        //refreshing
        create.setOnClickListener {
            /*list.removeAll(list);
            database.getCharacters().forEach { list.add(NewChar(it.id,it.name)) }
            playerList.adapter = CharAdapter(list, this)
            */
            val intent = Intent(this, CharCreation::class.java)
            startActivity(intent)

        }

    }

}

private class CharAdapter(val items : ArrayList<NewChar>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var hold: ViewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.character, parent, false))

        return hold;

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.character?.text = items.get(position).name;
        // each item on map gets an listener
        holder?.character?.setOnClickListener { Toast.makeText(context,items.get(position).id.toString(),Toast.LENGTH_LONG).show() }
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


