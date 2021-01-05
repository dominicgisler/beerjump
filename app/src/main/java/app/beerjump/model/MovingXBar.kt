package app.beerjump.model

import android.view.ViewGroup

class MovingXBar(gameView: ViewGroup, posX: Float, posY: Float, val minX: Float, val maxX: Float) : Bar(gameView, posX, posY) {
    var dir = 0.0f

    init {
        when ((0..1).random()) {
            0 -> dir = dpToPixels(gameView, 0.5f)
            1 -> dir = dpToPixels(gameView, -0.5f)
        }
    }

    override fun updateView(player: Player) {
        posX += dir
        if (item != null) {
            item!!.posX += dir
        }
        if (posX > maxX || posX < minX) {
            dir *= -1
        }
        super.updateView(player)
    }

    override fun jump(player: Player): Boolean {
        super.jump(player)
        Config.stats.cntMovingXBar++
        return true
    }
}