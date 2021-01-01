package app.beerjump.model

import android.view.ViewGroup
import app.beerjump.R

class Shot(gameView: ViewGroup, posX: Float, posY: Float) : Item(gameView, posX, posY) {
    val promille = 0.5

    init {
        initViews(width, height, R.drawable.ic_shot)
    }

    override fun pickup(player: Player) {
        SoundPlayer.shot.start()
        player.promille += promille
        Config.stats.cntShot++
    }
}