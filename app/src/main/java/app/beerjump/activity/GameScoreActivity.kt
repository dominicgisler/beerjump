package app.beerjump.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import app.beerjump.R
import app.beerjump.model.Config
import app.beerjump.model.Score
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_game_score.*
import org.json.JSONException
import org.json.JSONObject

class GameScoreActivity : AbstractActivity() {
    private val HIGHSCORE_URL = "https://api.beerjump.app/highscore/%s"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_score)

        val score = intent.getIntExtra("score", 0)
        val promille = intent.getDoubleExtra("promille", 0.0)

        scoreScore.text = score.toString()
        scorePromille.text = String.format("%.2f‰", promille)
        if (!Config.highscoreList.scores.isEmpty()) {
            scoreHighscore.text = Config.highscoreList.scores.first().score.toString()
        }

        name.setText(Config.highscoreList.lastUser)

        ok.setOnClickListener {
            val inputName = name.text.toString()
            if (inputName.isEmpty()) {
                name.error = getString(R.string.name_empty_error)
            } else {
                loadingView.visibility = View.VISIBLE
                val svScore = Score(inputName, promille, score, true)
                Config.stats.lastScore = score

                val queue = Volley.newRequestQueue(baseContext)

                val jsonObject = JSONObject()
                try {
                    jsonObject.put("name", svScore.username)
                    jsonObject.put("promille", svScore.promille)
                    jsonObject.put("score", svScore.score)
                } catch (e: JSONException) {
                }

                val req = JsonObjectRequest(
                    Request.Method.POST,
                    String.format(HIGHSCORE_URL, Config.uuid),
                    jsonObject,
                    {
                        if (it.getInt("statusCode") == 200) {
                            svScore.synced = true
                        }
                        Config.highscoreList.addScore(svScore)
                        Config.save()
                        startActivity(Intent(this, HighscoreActivity::class.java))
                    },
                    {
                        Config.highscoreList.addScore(svScore)
                        Config.save()
                        startActivity(Intent(this, HighscoreActivity::class.java))
                    }
                )
                queue.add(req)
            }
        }
    }
}