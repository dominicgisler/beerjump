package app.beerjump.model

import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import app.beerjump.R

class Bar(gameView: ViewGroup, posX: Int, posY: Int) : GuiElement(gameView, posX, posY) {
    companion object {
        var width = 180
        var height = 40
    }
    override var view = ImageView(gameView.context)

    init {
        gameView.addView(view)
        view.layoutParams.width = width
        view.layoutParams.height = height
        view.x = posX.toFloat()
        view.setImageDrawable(
            ContextCompat.getDrawable(
                gameView.context,
                R.drawable.ic_bar
            )
        )
    }
}