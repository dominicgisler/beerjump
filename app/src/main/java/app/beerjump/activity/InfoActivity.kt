package app.beerjump.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import app.beerjump.R
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AbstractActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        buttonData.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://beerjump.gisler-software.ch/datenschutz.html")
                )
            )
        }
        buttonMenu.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }
    }
}