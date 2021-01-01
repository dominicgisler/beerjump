package app.beerjump.model

import android.view.ViewGroup

abstract class Item(gameView: ViewGroup, posX: Float, posY: Float) : GuiElement(gameView, posX, posY) {
    companion object {
        var width = 0.0f
        var height = 0.0f

        fun calcSizes(gameView: ViewGroup) {
            width = dpToPixels(gameView, 28.0f)
            height = dpToPixels(gameView, 28.0f)
        }
    }

    abstract fun pickup(player: Player)
}