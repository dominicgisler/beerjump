package app.beerjump.model

import android.view.ViewGroup
import app.beerjump.R

class DisappearingBar(gameView: ViewGroup, posX: Float, posY: Float) : Bar(gameView, posX, posY) {
    init {
        initViews(width, height, R.drawable.ic_disappearing_bar)
    }

    override fun jump(player: Player): Boolean {
        super.jump(player)
        Config.stats.cntDisappearingBar++
        return false
    }
}