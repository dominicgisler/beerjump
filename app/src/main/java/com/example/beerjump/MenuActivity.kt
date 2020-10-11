package com.example.beerjump

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        buttonPlay.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
            finish()
        }
        buttonHighscore.setOnClickListener {
            startActivity(Intent(this, HighscoreActivity::class.java))
            finish()
        }
        buttonSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
            finish()
        }
    }
}