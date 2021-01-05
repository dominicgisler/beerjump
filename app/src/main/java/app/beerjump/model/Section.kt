package app.beerjump.model

import android.view.ViewGroup
import kotlin.random.Random

class Section(val gameView: ViewGroup, val startY: Float) {
    companion object {
        var height = 0.0f
        var padding = 0.0f
        var num = 0
        val difficultyStep = 50

        fun calcSizes(gameView: ViewGroup) {
            height = GuiElement.dpToPixels(gameView, 100.0f)
            padding = GuiElement.dpToPixels(gameView, 12.0f)
        }
    }
    val bars = ArrayList<Bar>()
    var difficulty = 1
    var maxBars = 5

    init {
        difficulty = (num / difficultyStep)
        maxBars = (maxBars - difficulty / 2)
        if (maxBars <= 0) {
            maxBars = 1
        }

        num++
        var numBars = (1..maxBars).random()
        if (num <= 1) {
            numBars = 5
        }
        val secWidth = gameView.width / numBars
        for (j in 0..numBars) {
            val minX = secWidth * j + padding
            val maxX = secWidth * (j + 1) - Bar.width - padding
            val minY = startY + padding
            val maxY = startY + height - padding
            val barX = ((Random.nextFloat() * (maxX - minX)) + minX)
            val barY = ((Random.nextFloat() * (maxY - minY)) + minY)

            val barVars = when (difficulty) {
                0 -> 0..3
                1 -> 0..4
                2 -> 0..5
                3 -> 0..6
                4 -> 0..7
                else -> 0..2
            }
            when (barVars.random()) {
                4 -> bars.add(MovingYBar(gameView, barX, barY, minY, maxY))
                5 -> bars.add(DisappearingBar(gameView, barX, barY))
                6 -> bars.add(MovingXBar(gameView, barX, barY, minX, maxX))
//                7 -> bars.add(TeleportingBar(gameView, barX, barY, minX, maxX, minY, maxY))
                else -> bars.add(Bar(gameView, barX, barY))
            }
        }
    }
}
