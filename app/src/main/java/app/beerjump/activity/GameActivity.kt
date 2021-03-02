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
import app.beerjump.model.Config
import app.beerjump.model.Game
import app.beerjump.model.SoundPlayer
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AbstractActivity(), SensorEventListener {
    var lastX = -1
    var pause = false
    var finished = false
    lateinit var game: Game
    lateinit var sensorManager: SensorManager
    lateinit var renderRun : Runnable
    var initialized = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        if (Config.inputMethod == "sensor") {
            sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
            sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }

        val scores = Config.highscoreList.scores
        var highscore = 0
        if (scores.size > 0) {
            highscore = scores.first().score
        }
        game = Game(gameLayout, highscore)
        Config.stats.plays++
        Config.save()
        Config.syncDevice()

        val startTime = System.currentTimeMillis()

        renderRun = object : Runnable {
            override fun run() {
                if (!game.step()) {
                    val seconds = ((System.currentTimeMillis() - startTime) / 1000).toInt()
                    Config.stats.lastDuration = seconds
                    Config.stats.durationTotal += seconds
                    if (seconds > Config.stats.durationTop) {
                        Config.stats.durationTop = seconds
                    }
                    Config.stats.lastScore = game.player.score
                    if (game.player.score > Config.stats.highScore) {
                        Config.stats.highScore = game.player.score
                    }
                    Config.stats.lastPromille = game.player.promille.toFloat()
                    if (game.player.promille > Config.stats.highestPromille) {
                        Config.stats.highestPromille = game.player.promille.toFloat()
                    }
                    Config.stats.falls++
                    Config.save()
                    Config.syncDevice()
                    val intent = Intent(baseContext, GameScoreActivity::class.java)
                    intent.putExtra("score", game.player.score)
                    intent.putExtra("promille", game.player.promille)
                    finished = true
                    startActivity(intent)
                    SoundPlayer.die.start()
                    finish()
                } else {
                    game.render()
                    if (!pause) {
                        gameView.postDelayed(this, 15)
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
            Config.stats.quits++
            Config.save()
            Config.syncDevice()
            startActivity(Intent(baseContext, MenuActivity::class.java))
            finish()
        }

        gameView.post {
            game.generate()
            gameView.post(renderRun)
            initialized = true
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
        if (!finished) {
            pause = true
            triggerPauseMenu()
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!initialized || pause || Config.inputMethod == "sensor") {
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
        if (!initialized || pause || Config.inputMethod == "touch") {
            return
        }
        if (event?.values != null) {
            val x = (event.values[0] * 3).toInt()
            game.player.direction = -x
        }
    }
}