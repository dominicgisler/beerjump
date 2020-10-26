package app.beerjump.activity

import android.content.Intent
import android.os.Bundle
import app.beerjump.R
import kotlinx.android.synthetic.main.activity_game_score.*

class GameScoreActivity : AbstractActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_score)
        val score = intent.getIntExtra("score", 0)
        val promille = intent.getDoubleExtra("promille", 0.0)
        val height = intent.getIntExtra("height", 0)
        scoreScore.text = score.toString()
        scorePromille.text = String.format("%.2f‰", promille)
        scoreHeight.text = height.toString()
        ok.setOnClickListener {
            //TODO: save highscore / name
            startActivity(Intent(this, MenuActivity::class.java))
        }
    }
}