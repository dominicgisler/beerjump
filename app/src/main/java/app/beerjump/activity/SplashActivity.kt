package app.beerjump.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import app.beerjump.R
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class SplashActivity : AbstractActivity() {
    private val SPLASH_TIME_OUT:Long = 3000 // 3 sec
    private val DEVICE_URL = "https://api.beerjump.app/device/%s"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val queue = Volley.newRequestQueue(baseContext)

        val jsonObject = JSONObject()
        try {
            jsonObject.put("sdk", android.os.Build.VERSION.SDK_INT)
            jsonObject.put("brand", android.os.Build.BRAND)
            jsonObject.put("model", android.os.Build.MODEL)
        } catch (e: JSONException) {}

        val req = JsonObjectRequest(Request.Method.PUT, String.format(DEVICE_URL, config.uuid), jsonObject, {}, {})
        queue.add(req)

        Handler().postDelayed({
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}