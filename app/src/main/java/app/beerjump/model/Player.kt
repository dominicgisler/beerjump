package app.beerjump.model

import android.view.ViewGroup
import androidx.core.content.ContextCompat
import app.beerjump.R

class Player(gameView: ViewGroup, posX: Float, posY: Float) : GuiElement(gameView, posX, posY) {
    companion object {
        var width = 0.0f
        var height = 0.0f

        fun calcSizes(gameView: ViewGroup) {
            width = dpToPixels(gameView, 56.0f)
            height = dpToPixels(gameView, 64.0f)
        }
    }

    var speed: Float = Bar.speedUp
    var direction = 0
    var promille = 0.0
    var score = 0

    init {
        initViews(width, height, R.drawable.ic_player_up)
        view.translationZ = 1f
        view.alpha = 1f
        gameView.removeView(view2)
    }

    fun updateView() {
        view.x = posX
        if (speed > 0) {
            view.setImageDrawable(
                ContextCompat.getDrawable(
                    gameView.context,
                    R.drawable.ic_player_up
                )
            )
        } else {
            view.setImageDrawable(
                ContextCompat.getDrawable(
                    gameView.context,
                    R.drawable.ic_player_down
                )
            )
        }
    }
}