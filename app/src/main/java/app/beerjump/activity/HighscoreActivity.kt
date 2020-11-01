package app.beerjump.activity

import android.content.Intent
import android.os.Bundle
import app.beerjump.R
import app.beerjump.model.ScoreAdapter
import kotlinx.android.synthetic.main.activity_highscore.*

class HighscoreActivity : AbstractActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highscore)

        val scores = config.highscoreList.scores
        scores.sortByDescending { it.score }
        var adapter = ScoreAdapter(scores, baseContext)
        highscoreList.adapter = adapter

        buttonMenu.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }
    }
}