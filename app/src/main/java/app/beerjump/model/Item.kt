package app.beerjump.model

import android.view.ViewGroup

abstract class Item(gameView: ViewGroup, posX: Int, posY: Int) : GuiElement(gameView, posX, posY) {
    companion object {
        val width = 70
        val height = 70
    }

    init {
        view.layoutParams.width = width
        view.layoutParams.height = height
        view.y = -height.toFloat()
    }

    abstract fun pickup(player: Player)
}