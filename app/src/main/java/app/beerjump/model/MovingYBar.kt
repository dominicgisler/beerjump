package app.beerjump.model

import android.view.ViewGroup

class MovingYBar(gameView: ViewGroup, posX: Float, posY: Float, val minY: Float, val maxY: Float) : Bar(gameView, posX, posY) {
    var dir = 0.0f

    init {
        when ((0..1).random()) {
            0 -> dir = dpToPixels(gameView, 0.5f)
            1 -> dir = dpToPixels(gameView, -0.5f)
        }
    }

    override fun updateView(player: Player) {
        posY += dir
        if (item != null) {
            item!!.posY += dir
        }
        if (posY > maxY || posY < minY) {
            dir *= -1
        }
        super.updateView(player)
    }

    override fun jump(player: Player) {
        super.jump(player)
        Config.stats.cntMovingYBar++
    }
}