package app.beerjump.activity

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import app.beerjump.R
import app.beerjump.model.Game
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AbstractActivity(), SensorEventListener {
    var lastX = -1
    var pause = false
    lateinit var game: Game
    lateinit var sensorManager: SensorManager
    lateinit var renderRun : Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        if (config.inputMethod == "sensor") {
            sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
            sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }

        val scores = config.highscoreList.scores
        var highscore = 0
        if (scores.size > 0) {
            highscore = scores.first().score
        }
        game = Game(gameLayout, highscore)

        renderRun = object : Runnable {
            override fun run() {
                if (!game.step()) {
                    val intent = Intent(baseContext, GameScoreActivity::class.java)
                    intent.putExtra("score", game.player.score)
                    intent.putExtra("promille", game.player.promille)
                    startActivity(intent)
                    finish()
                } else {
                    game.render()
                    if (!pause) {
                        gameView.postDelayed(this, 0)
                    }
                }
            }
        }

        buttonPause.setOnClickListener {
            pause = true
            triggerPauseMenu()
        }

        buttonContinueGame.setOnClickListener {
            pause = false
            triggerPauseMenu()
        }

        buttonEndGame.setOnClickListener {
            startActivity(Intent(baseContext, MenuActivity::class.java))
            finish()
        }

        gameView.post {
            game.generate()
            gameView.post(renderRun)
        }
    }

    fun triggerPauseMenu() {
        if (pause) {
            buttonPause.visibility = View.GONE
            pauseView.visibility = View.VISIBLE
        } else {
            pauseView.visibility = View.GONE
            buttonPause.visibility = View.VISIBLE
            gameView.post(renderRun)
        }
    }

    override fun onPause() {
        super.onPause()
        pause = true
        triggerPauseMenu()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (config.inputMethod == "sensor") {
            return false
        }
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

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (config.inputMethod == "touch") {
            return
        }
        if (event?.values != null) {
            val x = (event.values[0] * 3).toInt()
            game.player.direction = -x
        }
    }
}