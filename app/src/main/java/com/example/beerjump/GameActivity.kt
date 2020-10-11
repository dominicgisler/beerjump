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

const val barWIth = 240
const val barHeight = 40
const val playerWidth = 140
const val playerHeight = 160
const val numberOfBars = 12
const val ground = 0
const val speed = 10

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        val height = displayMetrics.heightPixels

        val bars = ArrayList<ImageView>()
        for (i in 0..numberOfBars) {
            val bar = ImageView(this)
            gameView.addView(bar)
            bar.layoutParams.width = barWIth
            bar.layoutParams.height = barHeight
            bar.x = Random.nextFloat() * (width - barWIth)
            bar.y = Random.nextFloat() * (height - barHeight)
            bar.setBackgroundColor(Color.GRAY)
            bars.add(bar)
        }

        playerView.layoutParams.width = playerWidth
        playerView.layoutParams.height = playerHeight

        val act = this
        var dirX = 0
        var dirY = speed
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                dirY = if (playerView.y <= ground) speed else dirY

                if (playerView.x < -playerWidth) {
                    playerView.x = width.toFloat()
                } else if (playerView.x > width) {
                    playerView.x = -playerWidth.toFloat()
                }

                // detect bar contact
                for (bar in bars) {
                    if ((playerView.x + playerWidth / 2) in bar.x..(bar.x + barWIth) && (playerView.y + playerHeight) in bar.y..(bar.y + barHeight)) {
                        dirY = -speed
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