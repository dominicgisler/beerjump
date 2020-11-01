package app.beerjump.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import app.beerjump.R
import kotlinx.android.synthetic.main.activity_highscore.buttonMenu
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AbstractActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        buttonMenu.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }
        buttonChangeBackground.setOnClickListener {
            startActivity(Intent(this, ChangeBackgroundActivity::class.java))
            finish()
        }
        buttonChangePlayer.setOnClickListener {
            startActivity(Intent(this, ChangePlayerActivity::class.java))
            finish()
        }
        buttonResetHighscore.setOnClickListener {
            config.highscoreList.scores.clear()
            config.save()
            Toast.makeText(applicationContext, "Highscore zurückgesetzt", Toast.LENGTH_SHORT).show()
        }
    }
}