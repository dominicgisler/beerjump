package app.beerjump.model

import android.content.Context
import android.content.SharedPreferences
import app.beerjump.BuildConfig
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import java.util.*

object Config {
    private val DEVICE_URL = "https://api.beerjump.app/device/%s"
    var highscoreList: HighscoreList = HighscoreList()
    var inputMethod = "touch"
    var uuid = ""
    var rating = ""
    var stats = Statistics()
    lateinit var sharedPref : SharedPreferences
    lateinit var context : Context

    fun init(sharedPref: SharedPreferences, context: Context) {
        this.sharedPref = sharedPref
        this.context = context

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
        rating = sharedPref.getString("rating", "").toString()

        // statistics
        stats.starts = sharedPref.getInt("starts", 1)
        stats.plays = sharedPref.getInt("plays", 0)
        stats.cntBar = sharedPref.getInt("cnt_bar", 0)
        stats.cntMovingXBar = sharedPref.getInt("cnt_moving_x_bar", 0)
        stats.cntMovingYBar = sharedPref.getInt("cnt_moving_y_bar", 0)
        stats.cntBeer = sharedPref.getInt("cnt_beer", 0)
        stats.cntShot = sharedPref.getInt("cnt_shot", 0)
        stats.cntRocket = sharedPref.getInt("cnt_rocket", 0)
        stats.durationTotal = sharedPref.getInt("duration_total", 0)
        stats.durationTop = sharedPref.getInt("duration_top", 0)
        stats.lastDuration = sharedPref.getInt("last_duration", 0)
        stats.highScore = sharedPref.getInt("high_score", 0)
        stats.lastScore = sharedPref.getInt("last_score", 0)
        stats.highestPromille = sharedPref.getFloat("highest_promille", 0.0f)
        stats.lastPromille = sharedPref.getFloat("last_promille", 0.0f)
        stats.falls = sharedPref.getInt("falls", 0)
        stats.quits = sharedPref.getInt("quits", 0)

        save()
    }

    fun save() {
        with (sharedPref.edit()) {
            val json = Gson().toJson(highscoreList)
            putString("highscore", json)
            putString("input", inputMethod)
            putString("uuid", uuid)
            putString("rating", rating)

            // statistics
            putInt("starts", stats.starts)
            putInt("plays", stats.plays)
            putInt("cnt_bar", stats.cntBar)
            putInt("cnt_moving_x_bar", stats.cntMovingXBar)
            putInt("cnt_moving_y_bar", stats.cntMovingYBar)
            putInt("cnt_beer", stats.cntBeer)
            putInt("cnt_shot", stats.cntShot)
            putInt("cnt_rocket", stats.cntRocket)
            putInt("duration_total", stats.durationTotal)
            putInt("duration_top", stats.durationTop)
            putInt("last_duration", stats.lastDuration)
            putInt("high_score", stats.highScore)
            putInt("last_score", stats.lastScore)
            putFloat("highest_promille", stats.highestPromille)
            putFloat("last_promille", stats.lastPromille)
            putInt("falls", stats.falls)
            putInt("quits", stats.quits)

            apply()
        }
    }

    fun syncDevice() {
        val queue = Volley.newRequestQueue(context)

        val jsonObject = JSONObject()
        try {
            jsonObject.put("sdk", android.os.Build.VERSION.SDK_INT)
            jsonObject.put("brand", android.os.Build.BRAND)
            jsonObject.put("model", android.os.Build.MODEL)
            jsonObject.put("last_user", highscoreList.lastUser)
            jsonObject.put("rating", rating)
            jsonObject.put("version", BuildConfig.VERSION_NAME)

            // statistics
            jsonObject.put("starts", stats.starts)
            jsonObject.put("plays", stats.plays)
            jsonObject.put("cnt_bar", stats.cntBar)
            jsonObject.put("cnt_moving_x_bar", stats.cntMovingXBar)
            jsonObject.put("cnt_moving_y_bar", stats.cntMovingYBar)
            jsonObject.put("cnt_beer", stats.cntBeer)
            jsonObject.put("cnt_shot", stats.cntShot)
            jsonObject.put("cnt_rocket", stats.cntRocket)
            jsonObject.put("duration_total", stats.durationTotal)
            jsonObject.put("duration_top", stats.durationTop)
            jsonObject.put("last_duration", stats.lastDuration)
            jsonObject.put("high_score", stats.highScore)
            jsonObject.put("last_score", stats.lastScore)
            jsonObject.put("highest_promille", stats.highestPromille)
            jsonObject.put("last_promille", stats.lastPromille)
            jsonObject.put("falls", stats.falls)
            jsonObject.put("quits", stats.quits)
        } catch (e: JSONException) {}

        val req = JsonObjectRequest(Request.Method.PUT, String.format(DEVICE_URL, uuid), jsonObject, {
            if (it.getInt("statusCode") == 200) {
                stats.starts = it.getJSONObject("data").getInt("starts")
                save()
            }
        }, {})
        queue.add(req)
    }

    fun resetData() {
        highscoreList.lastUser = ""
        highscoreList.scores.clear()
        inputMethod = "touch"
        uuid = UUID.randomUUID().toString()
        rating = ""
        stats = Statistics()
        save()
        syncDevice()
    }
}