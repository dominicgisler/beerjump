package app.beerjump.activity

import android.content.Intent
import android.os.Bundle
import app.beerjump.R
import kotlinx.android.synthetic.main.activity_about.buttonMenu

class AboutActivity : AbstractActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        buttonMenu.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }
    }
}