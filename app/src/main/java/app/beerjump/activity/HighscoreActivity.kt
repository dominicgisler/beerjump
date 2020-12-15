package app.beerjump.activity

import android.content.Intent
import android.os.Bundle
import app.beerjump.R
import app.beerjump.adapter.ScoreAdapter
import app.beerjump.model.Score
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_highscore.*
import org.json.JSONException
import org.json.JSONObject

class HighscoreActivity : AbstractActivity() {
    private val HIGHSCORE_URL = "https://api.beerjump.app/highscore/%s"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highscore)

        val adapter = ScoreAdapter(config.highscoreList.scores, baseContext)
        highscoreList.adapter = adapter

        for (score in config.highscoreList.scores) {
            if (score.own && !score.synced) {
                val queue = Volley.newRequestQueue(baseContext)

                val jsonObject = JSONObject()
                try {
                    jsonObject.put("name", score.username)
                    jsonObject.put("promille", score.promille)
                    jsonObject.put("score", score.score)
                } catch (e: JSONException) {}

                val req = JsonObjectRequest(
                    Request.Method.POST,
                    String.format(HIGHSCORE_URL, config.uuid),
                    jsonObject,
                    {
                        if (it.getInt("statusCode") == 200) {
                            score.synced = true
                            config.save()
                        }
                    },
                    {}
                )
                queue.add(req)
            }
        }

        val queue = Volley.newRequestQueue(baseContext)
        val req = JsonObjectRequest(
            Request.Method.GET,
            String.format(HIGHSCORE_URL, config.uuid),
            null,
            {
                if (it.getInt("statusCode") == 200) {
                    val scores = ArrayList<Score>()
                    val arr = it.getJSONArray("data")
                    for (i in 0 until arr.length()) {
                        val score = Score(
                            arr.getJSONObject(i).getString("name"),
                            arr.getJSONObject(i).getDouble("promille"),
                            arr.getJSONObject(i).getInt("score"),
                            arr.getJSONObject(i).getBoolean("own")
                        )
                        score.synced = true
                        scores.add(score)
                    }
                    config.highscoreList.scores = scores
                    config.save()
                    adapter.scores = scores
                    adapter.notifyDataSetChanged()
                }
            },
            {}
        )
        queue.add(req)

        buttonMenu.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }
    }
}