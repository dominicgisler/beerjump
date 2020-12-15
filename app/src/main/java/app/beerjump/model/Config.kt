package app.beerjump.model

import android.content.SharedPreferences
import com.google.gson.Gson
import java.lang.Exception
import java.util.*

class Config(val sharedPref: SharedPreferences) {
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
}