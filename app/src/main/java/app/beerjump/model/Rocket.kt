package app.beerjump.model

import android.view.ViewGroup
import app.beerjump.R

class Rocket(gameView: ViewGroup, posX: Float, posY: Float) : Item(gameView, posX, posY) {
    companion object {
        var speedUp = 0.0f
    }

    init {
        speedUp = dpToPixels(gameView, 38.0f)
        initViews(width, height, R.drawable.ic_rocket)
    }

    override fun pickup(player: Player) {
        SoundPlayer.rocket.start()
        player.speed = speedUp
        Config.stats.cntRocket++
    }
}