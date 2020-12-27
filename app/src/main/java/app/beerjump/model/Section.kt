package app.beerjump.model

import android.view.ViewGroup
import kotlin.random.Random

class Section(val gameView: ViewGroup, val startY: Int, val maxBars: Int) {
    companion object {
        var height = 0
        var padding = 0
        var num = 0

        fun calcSizes(gameView: ViewGroup) {
            val scale: Float = gameView.context.resources.displayMetrics.density
            height = (100 * scale + 0.5f).toInt()
            padding = (12 * scale + 0.5f).toInt()
        }
    }
    val bars = ArrayList<Bar>()

    init {
        num++
        var numBars = (1..(if (maxBars > 0) maxBars else 1)).random()
        if (num <= 1) {
            numBars = 5
        }
        val secWidth = gameView.width / numBars
        for (j in 0..numBars) {
            val minX = secWidth * j + padding
            val maxX = secWidth * (j + 1) - Bar.width - padding
            val minY = startY + padding
            val maxY = startY + height - padding
            val barX = ((Random.nextFloat() * (maxX - minX)) + minX).toInt()
            val barY = ((Random.nextFloat() * (maxY - minY)) + minY).toInt()

            var barVars = 0..2
            if (maxBars <= 4) {
                barVars = 0..3
                if (maxBars <= 3) {
                    barVars = 0..4
                }
            }
            when (barVars.random()) {
                0,1,2 -> bars.add(Bar(gameView, barX, barY))
                3 -> bars.add(MovingYBar(gameView, barX, barY, minY, maxY))
                4 -> bars.add(MovingXBar(gameView, barX, barY, minX, maxX))
            }
        }
    }
}
