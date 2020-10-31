package app.beerjump.model

import android.view.ViewGroup
import androidx.core.content.ContextCompat
import app.beerjump.R

class Shot(gameView: ViewGroup, posX: Int, posY: Int) : Item(gameView, posX, posY) {
    val promille = 0.2

    init {
        view.setImageDrawable(
            ContextCompat.getDrawable(
                gameView.context,
                R.drawable.ic_shot
            )
        )
    }

    override fun pickup(player: Player) {
        player.promille += promille
    }
}