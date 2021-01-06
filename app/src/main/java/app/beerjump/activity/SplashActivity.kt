package app.beerjump.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import app.beerjump.R
import app.beerjump.model.Config

class SplashActivity : AbstractActivity() {
    private val SPLASH_TIME_OUT: Long = 3000 // 3 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Config.init(this.getSharedPreferences("config", Context.MODE_PRIVATE), baseContext)
        Config.stats.starts++
        Config.save()
        Config.syncDevice()

        val intent = Intent(this, MenuActivity::class.java)
        if (Config.rating == "" && Config.stats.starts >= 6 || Config.rating == "remind" && Config.stats.starts % 6 == 0) {
            intent.putExtra("show_rate_dialog", true)
        } else {
            Config.checkVersion { hasUpdate, version ->
                if (hasUpdate) {
                    intent.putExtra("show_update_dialog", true)
                    intent.putExtra("latest_version", version)
                }
            }
        }

        Handler().postDelayed({
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)
    }
}