package app.beerjump.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import app.beerjump.R
import app.beerjump.adapter.ScoreAdapter
import app.beerjump.model.Config
import app.beerjump.model.Score
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.activity_highscore.*
import org.json.JSONException
import org.json.JSONObject

class HighscoreActivity : AbstractActivity() {
    private val HIGHSCORE_URL = "https://api.beerjump.gisler-software.ch/highscore/%s"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highscore)

        Config.highscoreList.scores.sortByDescending { it.score }
        val adapter = ScoreAdapter(Config.highscoreList.scores, baseContext)
        highscoreList.adapter = adapter

        val iter = Config.highscoreList.scores.iterator()
        while (iter.hasNext()) {
            val score = iter.next()
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
                    String.format(HIGHSCORE_URL, Config.uuid),
                    jsonObject,
                    {
                        if (it.getInt("statusCode") == 200) {
                            score.synced = true
                            Config.save()
                        }
                    },
                    {}
                )
                queue.add(req)
            } else if (!score.own) {
                iter.remove()
                Config.save()
            }
        }
        adapter.scores = Config.highscoreList.scores
        adapter.notifyDataSetChanged()

        highscoreTabs.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                progressBar.visibility = View.VISIBLE
                adapter.scores = ArrayList()
                adapter.notifyDataSetChanged()

                if (tab.position == 1) {
                    val queue = Volley.newRequestQueue(baseContext)
                    val req = JsonObjectRequest(
                        Request.Method.GET,
                        String.format(HIGHSCORE_URL, Config.uuid),
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
                                adapter.scores = scores
                                adapter.notifyDataSetChanged()
                                progressBar.visibility = View.GONE
                            }
                        },
                        {
                            progressBar.visibility = View.GONE
                        }
                    )
                    queue.add(req)
                } else {
                    adapter.scores = Config.highscoreList.scores
                    adapter.notifyDataSetChanged()
                    progressBar.visibility = View.GONE
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        buttonMenu.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }
    }
}