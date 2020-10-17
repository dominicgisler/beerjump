package app.beerjump.model

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class Game(val gameView: ViewGroup, val playerDrawable: Drawable?) {
    val speedUp = 30
    val sectionHeight = 250
    val sectionPadding = 30
    var height = 0

    val player: Player = Player()
    val bars: ArrayList<Bar> = ArrayList()

    fun generate() {
        // TODO: only generate visible sections
        for (i in 0..100) {
            addBarSection(i * sectionHeight)
        }
        player.posX = gameView.width / 2 - player.width / 2
        player.posY = 0
    }

    private fun addBarSection(startY: Int) {
        val numBars = (1..3).random()
        val secWidth = gameView.width / numBars
        for (j in 0..numBars) {
            val bar = Bar()
            val minX = secWidth * j + sectionPadding
            val maxX = secWidth * (j + 1) - bar.width - sectionPadding
            val minY = startY + sectionPadding
            val maxY = startY + sectionHeight - sectionPadding
            bar.posX = ((Random.nextFloat() * (maxX - minX)) + minX).toInt()
            bar.posY = ((Random.nextFloat() * (maxY - minY)) + minY).toInt()
            bars.add(bar)
        }
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
                if ((player.posX + player.width / 2) in bar.posX..(bar.posX + bar.width) && (player.posY) in bar.posY..(bar.posY + bar.height)) {
                    player.speed = speedUp
                }
            }
        }

        if (player.posY > (gameView.height / 2 + height) && player.speed > 0) {
            height += player.speed
        }

        if (player.posY - height + sectionHeight < 0) {
            return false
        } else {
            player.posX += player.direction
            player.posY += player.speed
            return true
        }
    }

    fun render() {
        gameView.removeAllViews()

        for (bar in bars) {
            val posY = (gameView.height - bar.posY - bar.height).toFloat() + height
            if (posY < -sectionHeight || posY > gameView.height + sectionHeight) {
                continue
            }
            val viewBar = ImageView(gameView.context)
            gameView.addView(viewBar)
            viewBar.layoutParams.width = bar.width
            viewBar.layoutParams.height = bar.height
            viewBar.x = bar.posX.toFloat()
            viewBar.y = posY
            viewBar.setBackgroundColor(Color.GRAY)
        }

        val playerView = ImageView(gameView.context)
        gameView.addView(playerView)
        playerView.setImageDrawable(playerDrawable)

        playerView.layoutParams.width = player.width
        playerView.layoutParams.height = player.height
        playerView.x = player.posX.toFloat()
        playerView.y = (gameView.height - player.posY - player.height).toFloat() + height

        val heightLabel = TextView(gameView.context)
        heightLabel.x = sectionPadding.toFloat()
        heightLabel.y = sectionPadding.toFloat()
        heightLabel.textSize = 18f
        heightLabel.text = "Aktuelle Höhe"
        val heightValue = TextView(gameView.context)
        heightValue.x = sectionPadding.toFloat()
        heightValue.y = heightLabel.y + 40
        heightValue.textSize = 32f
        heightValue.text = height.toString()
        gameView.addView(heightLabel)
        gameView.addView(heightValue)
    }

    fun setDirection(direction: Int) {
        player.direction = direction
    }

}