package app.beerjump.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import app.beerjump.R
import app.beerjump.adapter.StatisticsAdapter
import app.beerjump.model.Config
import app.beerjump.model.KeyValuePair
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_statistics.*

class StatisticsActivity : AbstractActivity() {
    private val STATISTICS_URL = "https://api.beerjump.app/statistics/%s"

    var stats = ArrayList<KeyValuePair>()
    lateinit var adapter : StatisticsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        adapter = StatisticsAdapter(stats, baseContext)
        statisticsList.adapter = adapter
        loadLocalStatistics()

        statisticsTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                progressBar.visibility = View.VISIBLE
                adapter.statistics = ArrayList()
                adapter.notifyDataSetChanged()

                when (tab.position) {
                    1 -> loadGlobalStatistics("average")
                    2 -> loadGlobalStatistics("total")
                    else -> loadLocalStatistics()
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

    fun secondsToTime(input: Int): String {
        val hours = input / 3600
        val minutes = (input % 3600) / 60
        val seconds = input % 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    fun loadLocalStatistics() {
        stats = ArrayList()
        stats.add(KeyValuePair(getString(R.string.app_started), Config.stats.starts.toString()))
        stats.add(KeyValuePair(getString(R.string.games_played), Config.stats.plays.toString()))
        stats.add(KeyValuePair("", ""))
        stats.add(KeyValuePair(getString(R.string.default_bars_jumped_on), Config.stats.cntBar.toString()))
        stats.add(KeyValuePair(getString(R.string.horizontal_moving_bars), Config.stats.cntMovingXBar.toString()))
        stats.add(KeyValuePair(getString(R.string.vertical_moving_bars), Config.stats.cntMovingYBar.toString()))
        stats.add(KeyValuePair("", ""))
        stats.add(KeyValuePair(getString(R.string.beers_drunk), Config.stats.cntBeer.toString()))
        stats.add(KeyValuePair(getString(R.string.shots_drunk), Config.stats.cntShot.toString()))
        stats.add(KeyValuePair(getString(R.string.rockets_used), Config.stats.cntRocket.toString()))
        stats.add(KeyValuePair("", ""))
        stats.add(KeyValuePair(getString(R.string.total_duration), secondsToTime(Config.stats.durationTotal)))
        stats.add(KeyValuePair(getString(R.string.longest_duration), secondsToTime(Config.stats.durationTop)))
        stats.add(KeyValuePair(getString(R.string.last_duration), secondsToTime(Config.stats.lastDuration)))
        stats.add(KeyValuePair("", ""))
        stats.add(KeyValuePair(getString(R.string.highest_score), Config.stats.highScore.toString()))
        stats.add(KeyValuePair(getString(R.string.last_score), Config.stats.lastScore.toString()))
        stats.add(KeyValuePair("", ""))
        stats.add(KeyValuePair(getString(R.string.highest_promille), String.format("%.2f‰", Config.stats.highestPromille)))
        stats.add(KeyValuePair(getString(R.string.last_promille), String.format("%.2f‰", Config.stats.lastPromille)))
        stats.add(KeyValuePair("", ""))
        stats.add(KeyValuePair(getString(R.string.falls), Config.stats.falls.toString()))
        stats.add(KeyValuePair(getString(R.string.cancelled_games), Config.stats.quits.toString()))
        adapter.statistics = stats
        adapter.notifyDataSetChanged()
        progressBar.visibility = View.GONE
    }

    fun loadGlobalStatistics(type: String) {
        val queue = Volley.newRequestQueue(baseContext)
        val req = JsonObjectRequest(
            Request.Method.GET,
            String.format(STATISTICS_URL, type),
            null,
            {
                if (it.getInt("statusCode") == 200) {
                    stats = ArrayList()
                    val obj = it.getJSONObject("data")

                    stats.add(KeyValuePair(getString(R.string.app_started), obj.getString("starts")))
                    stats.add(KeyValuePair(getString(R.string.games_played), obj.getString("plays")))
                    stats.add(KeyValuePair("", ""))
                    stats.add(KeyValuePair(getString(R.string.default_bars_jumped_on), obj.getString("cnt_bar")))
                    stats.add(KeyValuePair(getString(R.string.horizontal_moving_bars), obj.getString("cnt_moving_x_bar")))
                    stats.add(KeyValuePair(getString(R.string.vertical_moving_bars), obj.getString("cnt_moving_y_bar")))
                    stats.add(KeyValuePair("", ""))
                    stats.add(KeyValuePair(getString(R.string.beers_drunk), obj.getString("cnt_beer")))
                    stats.add(KeyValuePair(getString(R.string.shots_drunk), obj.getString("cnt_shot")))
                    stats.add(KeyValuePair(getString(R.string.rockets_used), obj.getString("cnt_rocket")))
                    stats.add(KeyValuePair("", ""))
                    stats.add(KeyValuePair(getString(R.string.total_duration), secondsToTime(obj.getInt("duration_total"))))
                    if (type == "average") {
                        stats.add(KeyValuePair(getString(R.string.longest_duration), secondsToTime(obj.getInt("duration_top"))))
                        stats.add(KeyValuePair(getString(R.string.last_duration), secondsToTime(obj.getInt("last_duration"))))
                        stats.add(KeyValuePair("", ""))
                        stats.add(KeyValuePair(getString(R.string.highest_score), obj.getString("high_score")))
                        stats.add(KeyValuePair(getString(R.string.last_score), obj.getString("last_score")))
                        stats.add(KeyValuePair("", ""))
                        stats.add(KeyValuePair(getString(R.string.highest_promille), String.format("%.2f‰", obj.getDouble("highest_promille"))))
                        stats.add(KeyValuePair(getString(R.string.last_promille), String.format("%.2f‰", obj.getDouble("last_promille"))))
                    }
                    stats.add(KeyValuePair("", ""))
                    stats.add(KeyValuePair(getString(R.string.falls), obj.getString("falls")))
                    stats.add(KeyValuePair(getString(R.string.cancelled_games), obj.getString("quits")))
                    adapter.statistics = stats
                    adapter.notifyDataSetChanged()

                    progressBar.visibility = View.GONE
                }
            },
            {
                progressBar.visibility = View.GONE
            }
        )
        queue.add(req)
    }
}