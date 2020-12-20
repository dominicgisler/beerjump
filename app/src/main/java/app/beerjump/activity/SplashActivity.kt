package app.beerjump.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import app.beerjump.R

class SplashActivity : AbstractActivity() {
    private val SPLASH_TIME_OUT:Long = 3000 // 3 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        config.starts++
        config.save()
        config.syncDevice()

        val intent = Intent(this, MenuActivity::class.java)
        if (config.rating != "never" && config.rating != "rate" && config.starts % 6 == 0) {
            intent.putExtra("show_rate_dialog", true)
        }

        Handler().postDelayed({
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)
    }
}