package app.beerjump.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import app.beerjump.R
import app.beerjump.model.Config
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
        buttonResetHighscore.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.reset_data))
                .setMessage(getString(R.string.reset_data_confirm))
                .setPositiveButton(
                    android.R.string.ok
                ) { _, _ ->
                    Config.resetData()
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.data_resetted),
                        Toast.LENGTH_SHORT
                    ).show()
                    setDecorView()
                }
                .setNegativeButton(android.R.string.cancel) { _, _ ->
                    setDecorView()
                }.show()
        }
        buttonChangeCharacter.setOnClickListener {
            startActivity(Intent(this, ChangeCharacterActivity::class.java))
        }
    }
}