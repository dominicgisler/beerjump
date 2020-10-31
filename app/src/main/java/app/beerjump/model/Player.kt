package app.beerjump.model

import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import app.beerjump.R

class Player(gameView: ViewGroup, posX: Int, posY: Int) : GuiElement(gameView, posX, posY) {
    companion object {
        val width = 140
        val height = 160
    }
    var speed = 30
    var direction = 0
    var promille = 0.0
    var score = 0
    override var view = ImageView(gameView.context)

    init {
        gameView.addView(view)
        view.layoutParams.width = width
        view.layoutParams.height = height
        view.x = posX.toFloat()
        view.setImageDrawable(
            ContextCompat.getDrawable(
                gameView.context,
                R.drawable.ic_player_up
            )
        )
    }
}