package app.beerjump.activity

import android.content.Intent
import android.os.Bundle
import app.beerjump.R
import app.beerjump.model.Score
import kotlinx.android.synthetic.main.activity_game_score.*

class GameScoreActivity : AbstractActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_score)

        val score = intent.getIntExtra("score", 0)
        val promille = intent.getDoubleExtra("promille", 0.0)

        scoreScore.text = score.toString()
        scorePromille.text = String.format("%.2f‰", promille)
        if (!config.highscoreList.scores.isEmpty()) {
            scoreHighscore.text = config.highscoreList.scores.first().score.toString()
        }

        name.setText(config.highscoreList.lastUser)

        ok.setOnClickListener {
            config.highscoreList.addScore(Score(name.text.toString(), promille, score))
            config.save()
            startActivity(Intent(this, HighscoreActivity::class.java))
        }
    }
}