package app.beerjump.model

import android.view.ViewGroup
import app.beerjump.R
import kotlin.random.Random

class TeleportingBar(gameView: ViewGroup, posX: Float, posY: Float, val minX: Float, val maxX: Float, val minY: Float, val maxY: Float) : Bar(gameView, posX, posY) {
    init {
        initViews(width, height, R.drawable.ic_teleporting_bar)
    }

    override fun jump(player: Player): Boolean {
        super.jump(player)
        Config.stats.cntTeleportingBar++
        posX = ((Random.nextFloat() * (maxX - minX)) + minX)
        posY = ((Random.nextFloat() * (maxY - minY)) + minY)
        view.x = posX
        view2.x = posX
        return true
    }
}