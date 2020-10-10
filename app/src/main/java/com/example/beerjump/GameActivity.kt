package com.example.beerjump

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.widget.ImageView
import android.widget.Toast
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

        for (i in 0..20) {
            val bar = ImageView(this)
            gameView.addView(bar)
            bar.layoutParams.width = 80
            bar.layoutParams.height = 20
            bar.x = Random.nextFloat() * (width - 80)
            bar.y = Random.nextFloat() * (height - 20)
            bar.setBackgroundColor(Color.GRAY)
        }

        playerView.layoutParams.width = 70
        playerView.layoutParams.height = 80

        var dirX = 1;
        var dirY = 1;
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                dirX = if (playerView.x <= 0) 1 else if (playerView.x >= (width - 70)) -1 else dirX;
                dirY = if (playerView.y <= 0) 1 else if (playerView.y >= (height - 80)) -1 else dirY;
                playerView.x += 10*dirX
                playerView.y += 10*dirY
                handler.postDelayed(this, 0)
            }
        })
    }
}