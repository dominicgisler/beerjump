package app.beerjump.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import app.beerjump.R
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    val barWidth = 240
    val barHeight = 40
    val playerWidth = 140
    val playerHeight = 160
    val numberOfBars = 8
    val speedUp = 30
    val startSpeed = -30
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        val height = displayMetrics.heightPixels

        val bars = ArrayList<ImageView>()
        val startBar = ImageView(this)
        gameView.addView(startBar)
        startBar.layoutParams.width = barWidth
        startBar.layoutParams.height = barHeight
        startBar.x = (width / 2 - barWidth / 2).toFloat()
        startBar.y = (height -220).toFloat()
        startBar.setBackgroundColor(Color.argb(
            1f, 0f, 0.8f, 0f
        ))
        bars.add(startBar)
        for (i in 0..numberOfBars) {
            val bar = ImageView(this)
            gameView.addView(bar)
            bar.layoutParams.width = barWidth
            bar.layoutParams.height = barHeight
            bar.x = Random.nextFloat() * (width - barWidth)
            bar.y = Random.nextFloat() * (height - barHeight)
            bar.setBackgroundColor(Color.GRAY)
            bars.add(bar)
        }

        playerView.layoutParams.width = playerWidth
        playerView.layoutParams.height = playerHeight
        playerView.x = (width / 2 - playerWidth / 2).toFloat()
        playerView.y = (height-playerHeight).toFloat()

        val act = this
        var dirX = 0
        var dirY = startSpeed
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                dirY++

                if (playerView.x < -playerWidth) {
                    playerView.x = width.toFloat()
                } else if (playerView.x > width) {
                    playerView.x = -playerWidth.toFloat()
                }

                // detect bar contact if going down
                if (dirY > 0) {
                    for (bar in bars) {
                        if ((playerView.x + playerWidth / 2) in bar.x..(bar.x + barWidth) && (playerView.y + playerHeight) in bar.y..(bar.y + barHeight)) {
                            dirY = -speedUp
                        }
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