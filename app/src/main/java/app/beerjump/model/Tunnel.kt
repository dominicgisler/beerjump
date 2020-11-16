package app.beerjump.model

import android.view.ViewGroup
import android.widget.ImageView
import app.beerjump.R

class Tunnel(gameView: ViewGroup, posX: Int, posY: Int) : GuiElement(gameView, posX, posY) {
    val width = 140
    val height = 160

    init {
        initViews(width, height, R.drawable.tunnel)
        view.translationZ = 2f
        view.alpha = 0f
        view.scaleType = ImageView.ScaleType.FIT_XY
        gameView.removeView(view2)
    }

    override fun updateView(player: Player) {
        val params = view.layoutParams
        params.width = (gameView.width * (7 - player.promille / 3)).toInt()
        params.height = (gameView.height * (7 - player.promille / 3)).toInt()
        view.x = player.view.x - view.width / 2 + player.view.width / 2
        view.y = player.view.y - view.height / 2 + player.view.height / 2
        view.layoutParams = params
        val tAlpha = (player.promille / 7).toFloat()
        if (view.alpha > tAlpha && tAlpha >= 0) {
            view.alpha -= 0.01.toFloat()
        } else if (view.alpha < tAlpha && tAlpha <= 1) {
            view.alpha += 0.01.toFloat()
        }
    }
}