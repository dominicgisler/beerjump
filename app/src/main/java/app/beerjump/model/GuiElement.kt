package app.beerjump.model

import android.view.ViewGroup
import android.widget.ImageView

open class GuiElement(val gameView: ViewGroup, var posX: Int, var posY: Int) {
    companion object {
        var width = 0
        var height = 0
    }
    var view = ImageView(gameView.context)

    init {
        gameView.addView(view)
        view.x = posX.toFloat()
    }

    open fun removeView() {
        if (view.parent == gameView) {
            gameView.removeView(view)
        }
    }

    open fun updateView() {}
}