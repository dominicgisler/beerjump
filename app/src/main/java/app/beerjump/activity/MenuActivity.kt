package app.beerjump.activity

import android.content.Intent
import android.os.Bundle
import app.beerjump.R
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AbstractActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        buttonPlay.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }
        buttonHighscore.setOnClickListener {
            startActivity(Intent(this, HighscoreActivity::class.java))
        }
        buttonSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        buttonAbout.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
    }
}