package com.example.beerjump

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
            Toast.makeText(this, "Highscore", Toast.LENGTH_LONG).show();
        }
        buttonSettings.setOnClickListener {
            startActivity(Intent(this, TestActivity::class.java))
            finish()
        }
    }
}