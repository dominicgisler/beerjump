package app.beerjump.model

import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random

class Game(val width: Int, val height: Int) {
    val numberOfBars = 8
    val speedUp = 30
    val startSpeed = -30

    val player: Player = Player()
    val bars: ArrayList<Bar> = ArrayList()

    fun init() {
        val startBar = Bar()
        startBar.posX = width / 2 - startBar.width / 2
        startBar.posY = height -220
        bars.add(startBar)
        for (i in 0..numberOfBars) {
            val bar = Bar()
            bar.posX = (Random.nextFloat() * (width - bar.width)).toInt()
            bar.posY = (Random.nextFloat() * (height - bar.height)).toInt()
            bars.add(bar)
        }
        player.posX = width / 2 - player.width / 2
        player.posY = height-player.height
    }

}