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
            bars.add(Bar(
                gameView,
                ((Random.nextFloat() * (maxX - minX)) + minX).toInt(),
                ((Random.nextFloat() * (maxY - minY)) + minY).toInt()
            ))
//            if (genBeer == j) {
//                val beer = Beer()
//                beer.posX = bar.posX + bar.width / 2 - beer.width / 2
//                beer.posY = bar.posY + bar.height + 20
//                beers.add(beer)
//            } else if (genShot == j) {
//                val shot = Shot()
//                shot.posX = bar.posX + bar.width / 2 - shot.width / 2
//                shot.posY = bar.posY + bar.height + 20
//                shots.add(shot)
//            }else if (genRocket == j) {
//                val rocket = Rocket()
//                rocket.posX = bar.posX + bar.width / 2 - rocket.width / 2
//                rocket.posY = bar.posY + bar.height + 20
//                rockets.add(rocket)
//            }
        }
    }
}
