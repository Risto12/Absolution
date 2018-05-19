package com.example.root.absolution2

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.character.*
import kotlinx.android.synthetic.main.character.view.*

class MainActivity : AppCompatActivity() {

    val list = ArrayList<String>();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.add("kissa");
        list.add("kirahvi")
        list.add("koira")

        playerList.layoutManager = LinearLayoutManager(this)

        playerList.adapter = AnimalAdapter(list, this)

    }

}

private class AnimalAdapter(val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.character, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.character?.text = items.get(position)
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }


}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val character = view.character
}


