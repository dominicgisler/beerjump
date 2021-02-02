package app.beerjump.model

import android.view.ViewGroup
import androidx.core.content.ContextCompat

class Player(gameView: ViewGroup, posX: Float, posY: Float) : GuiElement(gameView, posX, posY) {
    companion object {
        var width = 0.0f
        var height = 0.0f

        fun calcSizes(gameView: ViewGroup) {
            width = dpToPixels(gameView, 56.0f)
            height = dpToPixels(gameView, 56.0f)
        }
    }

    var speed: Float = Bar.speedUp
    var direction = 0
    var promille = 0.0
    var score = 0
    var drawableUp: Int = 0
    var drawableDown: Int = 0

    init {
        val context = gameView.context
        drawableUp = context.resources.getIdentifier(Config.playerDrawable.format("up"), "drawable", context.packageName)
        drawableDown = context.resources.getIdentifier(Config.playerDrawable.format("down"), "drawable", context.packageName)
        initViews(width, height, drawableUp)
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
                    drawableUp
                )
            )
        } else {
            view.setImageDrawable(
                ContextCompat.getDrawable(
                    gameView.context,
                    drawableDown
                )
            )
        }
    }
}