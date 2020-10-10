package com.example.beerjump

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        val height = displayMetrics.heightPixels

        val bars = ArrayList<ImageView>()
        for (i in 0..20) {
            val bar = ImageView(this)
            gameView.addView(bar)
            bar.layoutParams.width = 120
            bar.layoutParams.height = 20
            bar.x = Random.nextFloat() * (width - 120)
            bar.y = Random.nextFloat() * (height - 20)
            bar.setBackgroundColor(Color.GRAY)
            bars.add(bar)
        }

        playerView.layoutParams.width = 70
        playerView.layoutParams.height = 80

        val act = this
        var dirX = 0
        var dirY = 10
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                dirY = if (playerView.y <= 0) 10 else dirY

                if (playerView.x < -70) {
                    playerView.x = width * 1f
                } else if (playerView.x > width) {
                    playerView.x = -70f
                }

                // detect bar contact
                for (bar in bars) {
                    if ((playerView.x + 35) in bar.x..(bar.x + 120) && (playerView.y + 80) in bar.y..(bar.y + 20)) {
                        dirY = -10
                    }
                }

                if (playerView.y > height) {
                    startActivity(Intent(act, MenuActivity::class.java))
                    finish()
                } else {
                    playerView.x += dirX
                    playerView.y += dirY
                    handler.postDelayed(this, 0)
                }
            }
        })

        directionSlider.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                dirX = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }
}