package com.example.root.absolution2

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_char.*
import kotlinx.android.synthetic.main.activity_char_creation.*

class CharActivity : AppCompatActivity() {

    val ma = this;

    var lvl = 0;
    var hp = 0;
    var skill = 0;
    var mind = 0;
    var gold = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_char)
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)


        // SeekBars && arrows

        lvlUp.setOnClickListener {
            if(lvl < 15) {
                lvl += 1;
                charLVL.setText("LVL:" + lvl.toString())
            }
        }

        lvlDown.setOnClickListener {
            if(lvl != 0) {
                lvl -= 1;
                charLVL.setText("LVL:" + lvl.toString())
            }
        }

        hpBarChar.setMax(30)
        hpBarChar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //TODO
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                hp = progress
                charHP.setText("HP:" + hp)
            }
        })

        mindBarChar.setMax(30)
        mindBarChar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //TODO
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mind = progress
                charMind.setText("Mind:" + mind)
            }
        })
        skillBarChar.setMax(30)
        skillBarChar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //TODO
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                skill = progress
                charSkill.setText("Skill/Mana:" + skill)
            }
        })
        goldBarChar.setMax(2000)
        goldBarChar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //TODO
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                gold = progress
                charGold.setText("GOLD:" + gold)
            }
        })


        saveBT.setOnClickListener {
            finish()
        }

    }
}
