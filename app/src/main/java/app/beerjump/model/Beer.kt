package app.beerjump.model

import android.view.ViewGroup
import app.beerjump.R

class Beer(gameView: ViewGroup, posX: Int, posY: Int) : Item(gameView, posX, posY) {
    val promille = 0.2

    init {
        initViews(width, height, R.drawable.ic_beer)
    }

    override fun pickup(player: Player) {
        SoundPlayer.beer.start()
        player.promille += promille
    }
}