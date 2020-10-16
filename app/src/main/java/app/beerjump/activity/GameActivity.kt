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
import app.beerjump.model.Game
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

        val game = Game(width, height)


        for (bar in game.bars) {
            val viewBar = ImageView(this)
            gameView.addView(viewBar)
            viewBar.layoutParams.width = barWidth
            viewBar.layoutParams.height = barHeight
            viewBar.x = bar.posX.toFloat()
            viewBar.y = bar.posY.toFloat()
            viewBar.setBackgroundColor(Color.GRAY)
        }

        playerView.layoutParams.width = game.player.width
        playerView.layoutParams.height = game.player.height
        playerView.x = game.player.posX.toFloat()
        playerView.y = game.player.posY.toFloat()

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
                    for (bar in game.bars) {
                        if ((playerView.x + playerWidth / 2) in bar.posX..(bar.posX + barWidth) && (playerView.y + playerHeight) in bar.posY..(bar.posY + barHeight)) {
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