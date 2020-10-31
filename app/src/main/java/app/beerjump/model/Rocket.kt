package app.beerjump.model

import android.view.ViewGroup
import androidx.core.content.ContextCompat
import app.beerjump.R

class Rocket(gameView: ViewGroup, posX: Int, posY: Int) : Item(gameView, posX, posY) {
    val speed = 100

    init {
        view.setImageDrawable(
            ContextCompat.getDrawable(
                gameView.context,
                R.drawable.ic_rocket
            )
        )
    }

    override fun pickup(player: Player) {
        player.speed = speed
    }
}