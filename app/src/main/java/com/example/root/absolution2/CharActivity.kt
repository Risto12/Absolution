package com.example.root.absolution2

import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_char.*


class CharActivity : AppCompatActivity() {

    var database = DataBase(this)

    //dev
    val ma = this;

    var lvl = 0
    var hp = 0
    var skill = 0
    var mind = 0
    var gold = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_char)
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        val characterD = database.getCharacter(intent.getIntExtra("id",0))
        lvl = characterD.lvl
        hp = characterD.hp
        skill = characterD.skill
        mind = characterD.mind
        gold = characterD.gold

        nam.setText(characterD.name.toUpperCase() + " - " + characterD.type.toUpperCase())
        charLVL.setText("LVL:" + lvl)
        // SeekBars && arrows

        lvlUp.setOnClickListener {
            if(lvl < 10) {
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

        charHP.setText("HP:" + hp)
        hpBarChar.setMax(20)
        hpBarChar.setProgress(hp)
        hpBarChar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                hp = progress
                charHP.setText("HP:" + hp)
            }
        })

        charMind.setText("Mind:" + mind)
        mindBarChar.setMax(20)
        mindBarChar.setProgress(mind)
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


        charSkill.setText("Skill/Mana:" + skill)
        skillBarChar.setMax(20)
        skillBarChar.setProgress(skill)
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


        charGold.setText("GOLD:" + gold)
        goldBarChar.setMax(2000)
        goldBarChar.setProgress(gold)
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

        fun saveChar(){

            characterD.lvl = lvl
            characterD.hp = hp
            characterD.mind = mind
            characterD.skill = skill
            characterD.gold = gold

            database.updateCharacter(characterD)
        }

        saveBT.setOnClickListener {

            saveChar()

            finish()
        }

        playBT.setOnClickListener {

            saveChar()
            val intent = Intent(this, PlayActivity::class.java)
            intent.putExtra("hp",hp)
            intent.putExtra("mind",mind)
            intent.putExtra("skill",skill)

            startActivity(intent)
        }

    }
}
