package app.beerjump.activity

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import app.beerjump.R
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AbstractActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        if (intent.getBooleanExtra("show_rate_dialog", false)) {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.rate_title))
                .setMessage(getString(R.string.rate_description))
                .setPositiveButton(R.string.rate_now) { _, _ ->
                    config.rating = "rate"
                    config.save()
                    config.syncDevice()
                    val appPackageName = packageName
                    try {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("market://details?id=$appPackageName")
                            )
                        )
                    } catch (anfe: ActivityNotFoundException) {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                            )
                        )
                    }
                    setDecorView()
                }
                .setNegativeButton(R.string.no_thanks) { _, _ ->
                    config.rating = "never"
                    config.save()
                    config.syncDevice()
                    setDecorView()
                }
                .setNeutralButton(R.string.remind_me_later) { _, _ ->
                    config.rating = "remind"
                    config.save()
                    config.syncDevice()
                    setDecorView()
                }.show()
        }

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