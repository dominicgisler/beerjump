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

        config.syncDevice()

        Handler().postDelayed({
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}