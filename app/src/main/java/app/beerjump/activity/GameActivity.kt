package app.beerjump.activity

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import app.beerjump.R
import app.beerjump.model.Game
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    var lastX = -1
    lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        game = Game(gameLayout)
        val act = this

        val renderRun = object : Runnable {
            override fun run() {
                if (!game.step()) {
                    startActivity(Intent(act, MenuActivity::class.java))
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
                game.setDirection(-lastX + x)
            }
            lastX = x
        } else if (event.action == MotionEvent.ACTION_UP) {
            game.setDirection(0)
            lastX = -1
        }
        return false
    }
}