package app.beerjump.model

import android.view.ViewGroup

class MovingYBar(gameView: ViewGroup, posX: Int, posY: Int, val minY: Int, val maxY: Int) : Bar(gameView, posX, posY) {
    var dir = 1

    override fun removeView() {
        super.removeView()
        if (item != null) {
            item!!.removeView()
        }
    }

    override fun updateView(player: Player) {
        posY += dir
        if (item != null) {
            item!!.posY += dir
        }
        if (posY > maxY) {
            dir = -1
        } else if (posY < minY) {
            dir = 1
        }
        super.updateView(player)
    }
}