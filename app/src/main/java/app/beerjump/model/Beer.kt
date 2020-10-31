package app.beerjump.model

import android.view.ViewGroup
import androidx.core.content.ContextCompat
import app.beerjump.R

class Beer(gameView: ViewGroup, posX: Int, posY: Int) : Item(gameView, posX, posY) {
    val promille = 0.2

    init {
        view.layoutParams.width = width
        view.layoutParams.height = height
        view.y = -height.toFloat()
        view.setImageDrawable(
            ContextCompat.getDrawable(
                gameView.context,
                R.drawable.ic_beer
            )
        )
    }

    override fun pickup(player: Player) {
        player.promille += promille
    }
}