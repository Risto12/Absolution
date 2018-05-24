package com.example.root.absolution2

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_char_creation.*

class CharCreationActivity : AppCompatActivity() {

    val CAMERA_REQUEST_CODE = 0

    var hp = 0;
    var skill = 0;
    var mind = 0;

    var database = DataBase(this);


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_char_creation)
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //dev
        val contx = this;

        //Take picture
        camPic.setOnClickListener {

            val camIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(camIntent.resolveActivity(packageManager) != null){
                startActivityForResult(camIntent, CAMERA_REQUEST_CODE)
            }
        }







        //SeekBars

        skillBar.setMax(10)

        skillBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //TODO
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                skillView.setText("SKILL/MANA: " + progress)
                skill = progress;
            }
        })


        mindBar.setMax(10)

        mindBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //TODO
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mindView.setText("MIND: " + progress)
                mind = progress
            }
        })



        hpBar.setMax(10)

        hpBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //TODO
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                hpView.setText("HP: " + progress)
                hp = progress;
            }
        })

        returnBT.setOnClickListener {

           database.createCharacter(NewChar(0,charNam.text.toString(),charType.text.toString(),0,hp,mind,skill,null,0))
            finish()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            CAMERA_REQUEST_CODE -> {
                if(resultCode == Activity.RESULT_OK && data != null){
                    
                    camPic.setImageBitmap(data.extras.get("data") as Bitmap)
                }
            }else -> {
            Toast.makeText(this,"permission denied", Toast.LENGTH_SHORT).show();
        }
        }

    }


}


