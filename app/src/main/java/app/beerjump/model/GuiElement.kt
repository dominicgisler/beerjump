package app.beerjump.model

import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat

open class GuiElement(val gameView: ViewGroup, var posX: Int, var posY: Int) {
    companion object {
        var width = 0
        var height = 0
    }

    var view = ImageView(gameView.context)
    var view2 = ImageView(gameView.context)

    init {
        gameView.addView(view)
        gameView.addView(view2)
        view.x = posX.toFloat()
        view2.x = posX.toFloat()
    }

    fun initViews(width: Int, height: Int, drawableId: Int) {
        view.layoutParams.width = width
        view2.layoutParams.width = width
        view.layoutParams.height = height
        view2.layoutParams.height = height
        view.y = -height.toFloat()
        view2.y = -height.toFloat()
        view.setImageDrawable(ContextCompat.getDrawable(gameView.context, drawableId))
        view2.setImageDrawable(ContextCompat.getDrawable(gameView.context, drawableId))
        view.alpha = 0.7f
        view2.alpha = 0.7f
    }

    open fun removeView() {
        if (view.parent == gameView) {
            gameView.removeView(view)
        }
        if (view2.parent == gameView) {
            gameView.removeView(view2)
        }
    }

    open fun updateView(player: Player) {
        view.x = (posX - player.promille * 10).toFloat()
        view2.x = (posX + player.promille * 10).toFloat()
        val alpha = (0.7 - player.promille / 10).toFloat()
        view.alpha = if (alpha > 0.4) alpha else 0.4f
        view2.alpha = if (alpha > 0.4) alpha else 0.4f
    }
}