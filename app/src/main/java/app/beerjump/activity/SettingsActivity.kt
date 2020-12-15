package app.beerjump.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import app.beerjump.R
import kotlinx.android.synthetic.main.activity_highscore.buttonMenu
import kotlinx.android.synthetic.main.activity_settings.*
import java.util.*

class SettingsActivity : AbstractActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        if (config.inputMethod == "sensor") {
            buttonInputToggle.isChecked = true
        }

        buttonMenu.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }
        buttonResetHighscore.setOnClickListener {
            config.highscoreList.scores.clear()
            config.uuid = UUID.randomUUID().toString()
            config.save()
            config.syncDevice()
            Toast.makeText(applicationContext, getString(R.string.highscore_resetted), Toast.LENGTH_SHORT).show()
        }
        buttonInputToggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                config.inputMethod = "sensor"
            } else {
                config.inputMethod = "touch"
            }
            config.save()
        }
    }
}