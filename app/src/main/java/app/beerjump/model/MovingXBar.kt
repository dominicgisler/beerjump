package app.beerjump.model

import android.view.ViewGroup

class MovingXBar(gameView: ViewGroup, posX: Int, posY: Int, val minX: Int, val maxX: Int) : Bar(gameView, posX, posY) {
    var dir = 1

    override fun removeView() {
        super.removeView()
        if (item != null) {
            item!!.removeView()
        }
    }

    override fun updateView(player: Player) {
        posX += dir
        if (item != null) {
            item!!.posX += dir
        }
        if (posX > maxX) {
            dir = -1
        } else if (posX < minX) {
            dir = 1
        }
        super.updateView(player)
    }
}