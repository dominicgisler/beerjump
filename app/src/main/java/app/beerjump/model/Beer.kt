package app.beerjump.model

import android.view.ViewGroup

class Beer(gameView: ViewGroup, posX: Int, posY: Int) : GuiElement(gameView, posX, posY) {
    companion object {
        val width = 70
        val height = 70
    }
}