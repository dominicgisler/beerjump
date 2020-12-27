package app.beerjump.model

import android.view.ViewGroup

abstract class Item(gameView: ViewGroup, posX: Int, posY: Int) : GuiElement(gameView, posX, posY) {
    companion object {
        var width = 0
        var height = 0

        fun calcSizes(gameView: ViewGroup) {
            val scale: Float = gameView.context.resources.displayMetrics.density
            width = (28 * scale + 0.5f).toInt()
            height = (28 * scale + 0.5f).toInt()
        }
    }

    abstract fun pickup(player: Player)
}