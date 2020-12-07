package app.beerjump.model

import android.view.ViewGroup
import app.beerjump.R

class Rocket(gameView: ViewGroup, posX: Int, posY: Int) : Item(gameView, posX, posY) {
    val speed = 100

    init {
        initViews(width, height, R.drawable.ic_rocket)
    }

    override fun pickup(player: Player) {
        SoundPlayer.rocket.start()
        player.speed = speed
    }
}