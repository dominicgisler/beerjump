package app.beerjump.model

import android.view.ViewGroup
import androidx.core.content.ContextCompat
import app.beerjump.R

class Player(gameView: ViewGroup, posX: Int, posY: Int) : GuiElement(gameView, posX, posY) {
    companion object {
        var width = 0
        var height = 0

        fun calcSizes(gameView: ViewGroup) {
            val scale: Float = gameView.context.resources.displayMetrics.density
            width = (56 * scale + 0.5f).toInt()
            height = (64 * scale + 0.5f).toInt()
        }
    }
    var speed = 30
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
        view.x = posX.toFloat()
        if (speed > 0) {
            view.setImageDrawable(ContextCompat.getDrawable(gameView.context, R.drawable.ic_player_up))
        } else {
            view.setImageDrawable(ContextCompat.getDrawable(gameView.context, R.drawable.ic_player_down))
        }
    }
}