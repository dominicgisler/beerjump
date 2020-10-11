package com.example.beerjump

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_highscore.*

class HighscoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highscore)

        val act = this
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                handler.postDelayed(this, 0)
                buttonMenu.setOnClickListener {
                    startActivity(Intent(act, MenuActivity::class.java))
                    finish()
                }
            }
        })
    }
}