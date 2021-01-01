package app.beerjump.model

import android.view.ViewGroup
import android.widget.ImageView
import app.beerjump.R

class Tunnel(gameView: ViewGroup, posX: Float, posY: Float) : GuiElement(gameView, posX, posY) {
    val width = gameView.width * 4.0f
    val height = gameView.height * 3.0f

    init {
        initViews(width, height, R.drawable.tunnel)
        view.translationZ = 2f
        view.alpha = 0f
        view.scaleType = ImageView.ScaleType.FIT_XY
        gameView.removeView(view2)
    }

    override fun updateView(player: Player) {
        view.x = player.view.x - view.width / 2 + player.view.width / 2
        view.y = player.view.y - view.height / 2 + player.view.height / 2
        adjustAlpha(view, (player.promille / 5).toFloat())
    }
}