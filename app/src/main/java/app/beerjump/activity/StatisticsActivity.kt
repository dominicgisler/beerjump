package app.beerjump.activity

import android.content.Intent
import android.os.Bundle
import app.beerjump.R
import app.beerjump.adapter.StatisticsAdapter
import app.beerjump.model.Config
import app.beerjump.model.KeyValuePair
import kotlinx.android.synthetic.main.activity_statistics.*
import kotlinx.android.synthetic.main.activity_statistics.buttonMenu

class StatisticsActivity : AbstractActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        val stats = ArrayList<KeyValuePair>()
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
        stats.add(KeyValuePair(getString(R.string.last_duration), Config.stats.lastScore.toString()))
        stats.add(KeyValuePair("", ""))
        stats.add(KeyValuePair(getString(R.string.falls), Config.stats.falls.toString()))
        stats.add(KeyValuePair(getString(R.string.cancelled_games), Config.stats.quits.toString()))

        val adapter = StatisticsAdapter(stats, baseContext)
        statisticsList.adapter = adapter

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
}