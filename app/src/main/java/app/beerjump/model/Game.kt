package app.beerjump.model

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.widget.ImageView
import kotlin.random.Random


class Game(val gameView: ViewGroup, val playerDrawable: Drawable?) {
    val numberOfBars = 8
    val speedUp = 30

    val player: Player = Player()
    val bars: ArrayList<Bar> = ArrayList()

    fun generate() {
        val startBar = Bar()
        startBar.posX = gameView.width / 2 - startBar.width / 2
        startBar.posY = gameView.height -220
        bars.add(startBar)
        for (i in 0..numberOfBars) {
            val bar = Bar()
            bar.posX = (Random.nextFloat() * (gameView.width - bar.width)).toInt()
            bar.posY = (Random.nextFloat() * (gameView.height - bar.height)).toInt()
            bars.add(bar)
        }
        player.posX = gameView.width / 2 - player.width / 2
        player.posY = gameView.height-player.height
    }

    fun step(): Boolean {
        player.speed--

        if (player.posX < -player.width) {
            player.posX = gameView.width
        } else if (player.posX > gameView.width) {
            player.posX = -player.width
        }

        // detect bar contact if going down
        if (player.speed < 0) {
            for (bar in bars) {
                if ((player.posX + player.width / 2) in bar.posX..(bar.posX + bar.width) && (player.posY + player.height) in bar.posY..(bar.posY + bar.height)) {
                    player.speed = speedUp
                }
            }
        }

        if (player.posY > gameView.height) {
            return false
        } else {
            player.posX += player.direction
            player.posY += -player.speed
            return true
        }
    }

    fun render() {
        gameView.removeAllViews()

        for (bar in bars) {
            val viewBar = ImageView(gameView.context)
            gameView.addView(viewBar)
            viewBar.layoutParams.width = bar.width
            viewBar.layoutParams.height = bar.height
            viewBar.x = bar.posX.toFloat()
            viewBar.y = bar.posY.toFloat()
            viewBar.setBackgroundColor(Color.GRAY)
        }

        val playerView = ImageView(gameView.context)
        gameView.addView(playerView)
        playerView.setImageDrawable(playerDrawable)

        playerView.layoutParams.width = player.width
        playerView.layoutParams.height = player.height
        playerView.x = player.posX.toFloat()
        playerView.y = player.posY.toFloat()
    }

    fun setDirection(direction: Int) {
        player.direction = direction
    }

}