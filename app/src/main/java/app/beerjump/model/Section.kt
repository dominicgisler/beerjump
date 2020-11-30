package app.beerjump.model

import android.view.ViewGroup
import kotlin.random.Random

class Section(val gameView: ViewGroup, val startY: Int, val maxBars: Int) {
    companion object {
        val height = 250
        val padding = 30
    }
    val bars = ArrayList<Bar>()

    init {
        val numBars = (1..(if (maxBars > 0) maxBars else 1)).random()
        val secWidth = gameView.width / numBars
        for (j in 0..numBars) {
            val minX = secWidth * j + padding
            val maxX = secWidth * (j + 1) - Bar.width - padding
            val minY = startY + padding
            val maxY = startY + height - padding
            val barX = ((Random.nextFloat() * (maxX - minX)) + minX).toInt()
            val barY = ((Random.nextFloat() * (maxY - minY)) + minY).toInt()

            var barVars = 0..1
            if (maxBars <= 3) {
                barVars = 0..2
            }
            when (barVars.random()) {
                0,1 -> bars.add(Bar(gameView, barX, barY))
                2 -> bars.add(MovingBar(gameView, barX, barY, minX, maxX))
            }
        }
    }
}
