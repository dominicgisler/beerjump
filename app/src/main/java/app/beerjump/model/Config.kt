package app.beerjump.model

import android.content.SharedPreferences
import com.google.gson.Gson
import java.lang.Exception

class Config(val sharedPref: SharedPreferences) {
    var highscoreList: HighscoreList = HighscoreList()
    var inputMethod = "touch"

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
    }

    fun save() {
        with (sharedPref.edit()) {
            val json = Gson().toJson(highscoreList)
            putString("highscore", json)
            putString("input", inputMethod)
            apply()
        }
    }
}