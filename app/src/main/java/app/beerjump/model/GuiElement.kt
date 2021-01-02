package app.beerjump.model

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat

open class GuiElement(val gameView: ViewGroup, var posX: Float, var posY: Float) {
    companion object {
        var width = 0.0f
        var height = 0.0f

        fun getDpPixelScale(gameView: ViewGroup): Float {
            var scale = gameView.context.resources.displayMetrics.density
            val widthDp = (gameView.width / scale)
            val minWidthDp = 420
            if (widthDp < minWidthDp) {
                scale *= (widthDp / minWidthDp)
            }
            return scale
        }

        fun dpToPixels(gameView: ViewGroup, dp: Float): Float {
            return (dp * getDpPixelScale(gameView))
        }

        fun pixelsToDp(gameView: ViewGroup, pixels: Float): Float {
            return (pixels / getDpPixelScale(gameView))
        }
    }

    var view = ImageView(gameView.context)
    var view2 = ImageView(gameView.context)

    init {
        gameView.addView(view)
        gameView.addView(view2)
        view.x = posX
        view2.x = posX
    }

    fun initViews(width: Float, height: Float, drawableId: Int) {
        view.layoutParams.width = width.toInt()
        view2.layoutParams.width = width.toInt()
        view.layoutParams.height = height.toInt()
        view2.layoutParams.height = height.toInt()
        view.y = -height
        view2.y = -height
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
        moveToX(view, (posX - dpToPixels(gameView, (player.promille * 2).toFloat())))
        moveToX(view2, (posX + dpToPixels(gameView, (player.promille * 2).toFloat())))
        val alpha = (0.7 - player.promille / 10).toFloat()
        adjustAlpha(view, if (alpha > 0.4) alpha else 0.4f)
        adjustAlpha(view2, if (alpha > 0.4) alpha else 0.4f)
    }

    fun moveToX(view: View, x: Float) {
        if (view.x > x) {
            view.x--
        } else if (view.x < x) {
            view.x++
        }
    }

    fun adjustAlpha(view: View, alpha: Float) {
        if (view.alpha > alpha) {
            view.alpha -= 0.005f
        } else if (view.alpha < alpha) {
            view.alpha += 0.005f
        }
    }
}