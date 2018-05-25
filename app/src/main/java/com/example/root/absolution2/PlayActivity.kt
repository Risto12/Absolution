package com.example.root.absolution2

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_play.*

class PlayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)



        hpPlay.setText("HP:" + intent.getIntExtra("hp",0))
        playHpSeek.setMax(intent.getIntExtra("hp",0))
        playHpSeek.setProgress(intent.getIntExtra("hp",0))
        playHpSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                hpPlay.setText("HP:" + progress)
            }
        })

        skillPlay.setText("SKill/Mana:" + intent.getIntExtra("skill",0))
        playSkillSeek.setMax(intent.getIntExtra("skill",0))
        playSkillSeek.setProgress(intent.getIntExtra("skill",0))
        playSkillSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                skillPlay.setText("Skill/Mana:" + progress)
            }
        })

        mindPlay.setText("Mind:" + intent.getIntExtra("mind",0))
        playMindSeek.setMax(intent.getIntExtra("mind",0))
        playMindSeek.setProgress(intent.getIntExtra("mind",0))
        playMindSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mindPlay.setText("Mind:" + progress)
            }
        })

        playReturnBT.setOnClickListener {
            finish()
        }
    }
}
