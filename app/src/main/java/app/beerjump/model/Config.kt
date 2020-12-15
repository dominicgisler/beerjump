package app.beerjump.model

import android.content.Context
import android.content.SharedPreferences
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import java.util.*

class Config(val sharedPref: SharedPreferences, val baseContext: Context) {
    private val DEVICE_URL = "https://api.beerjump.app/device/%s"
    var highscoreList: HighscoreList = HighscoreList()
    var inputMethod = "touch"
    var uuid = ""

    init {
        val data = sharedPref.getString("highscore", "{\"last_user\":\"\",\"scores\":[]}")
        if (data != null) {
            try {
                highscoreList = Gson().fromJson<HighscoreList>(data, HighscoreList::class.java)
            } catch (e: Exception) {
                save()
            }
        }
        inputMethod = sharedPref.getString("input", "touch").toString()
        uuid = sharedPref.getString("uuid", UUID.randomUUID().toString()).toString()
        save()
    }

    fun save() {
        with (sharedPref.edit()) {
            val json = Gson().toJson(highscoreList)
            putString("highscore", json)
            putString("input", inputMethod)
            putString("uuid", uuid)
            apply()
        }
    }

    fun syncDevice() {
        val queue = Volley.newRequestQueue(baseContext)

        val jsonObject = JSONObject()
        try {
            jsonObject.put("sdk", android.os.Build.VERSION.SDK_INT)
            jsonObject.put("brand", android.os.Build.BRAND)
            jsonObject.put("model", android.os.Build.MODEL)
        } catch (e: JSONException) {}

        val req = JsonObjectRequest(Request.Method.PUT, String.format(DEVICE_URL, uuid), jsonObject, {}, {})
        queue.add(req)
    }
}