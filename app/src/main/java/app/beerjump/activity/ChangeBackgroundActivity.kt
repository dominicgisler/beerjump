package app.beerjump.activity

import android.content.Intent
import android.os.Bundle
import app.beerjump.R
import kotlinx.android.synthetic.main.activity_highscore.*

class ChangeBackgroundActivity : AbstractActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_background)

        buttonMenu.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
            finish()
        }
    }
}