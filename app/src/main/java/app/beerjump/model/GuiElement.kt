package app.beerjump.model

import android.view.View
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
        moveToX(view, (posX - player.promille * 5))
        moveToX(view2, (posX + player.promille * 5))
        val alpha = (0.7 - player.promille / 10).toFloat()
        adjustAlpha(view, if (alpha > 0.4) alpha else 0.4f)
        adjustAlpha(view2, if (alpha > 0.4) alpha else 0.4f)
    }

    fun moveToX(view: View, x: Double) {
        if (view.x > x) {
            view.x--
        } else if (view.x < x) {
            view.x++
        }
    }

    fun moveToY(view: View, x: Double) {
        if (view.x > x) {
            view.x--
        } else if (view.x < x) {
            view.x++
        }
    }

    fun adjustAlpha(view: View, alpha: Float) {
        if (view.alpha > alpha) {
            view.alpha -= 0.01f
        } else if (view.alpha < alpha) {
            view.alpha += 0.01f
        }
    }
}