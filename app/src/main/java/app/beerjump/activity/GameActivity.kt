package app.beerjump.activity

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import app.beerjump.R
import app.beerjump.model.Game
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AbstractActivity() {
    var lastX = -1
    lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val scores = config.highscoreList.scores
        var highscore = 0
        if (scores.size > 0) {
            highscore = scores.first().score
        }
        game = Game(gameLayout, highscore)
        val act = this

        val renderRun = object : Runnable {
            override fun run() {
                if (!game.step()) {
                    val intent = Intent(act, GameScoreActivity::class.java)
                    intent.putExtra("score", game.player.score)
                    intent.putExtra("promille", game.player.promille)
                    startActivity(intent)
                    finish()
                } else {
                    game.render()
                    gameView.postDelayed(this, 0)
                }
            }
        }

        gameView.post {
            game.generate()
            gameView.post(renderRun)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x.toInt()
        if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {
            if (lastX != -1) {
                game.player.direction = -lastX + x
            }
            lastX = x
        } else if (event.action == MotionEvent.ACTION_UP) {
            game.player.direction = 0
            lastX = -1
        }
        return false
    }
}