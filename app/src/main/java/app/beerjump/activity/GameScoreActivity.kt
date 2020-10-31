package app.beerjump.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import app.beerjump.R
import app.beerjump.model.Config
import app.beerjump.model.Score
import kotlinx.android.synthetic.main.activity_game_score.*

class GameScoreActivity : AbstractActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_score)

        val cfg = Config(this.getPreferences(Context.MODE_PRIVATE))
        val score = intent.getIntExtra("score", 0)
        val promille = intent.getDoubleExtra("promille", 0.0)
        val height = intent.getIntExtra("height", 0)

        scoreScore.text = score.toString()
        scorePromille.text = String.format("%.2f‰", promille)
        scoreHeight.text = height.toString()
        name.setText(cfg.highscoreList.lastUser)

        ok.setOnClickListener {
            cfg.highscoreList.addScore(Score(name.text.toString(), promille, score))
            cfg.save()
            startActivity(Intent(this, MenuActivity::class.java))
        }
    }
}