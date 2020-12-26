package app.beerjump.model

import android.view.ViewGroup
import app.beerjump.R
import kotlin.random.Random

open class Bar(gameView: ViewGroup, posX: Int, posY: Int) : GuiElement(gameView, posX, posY) {
    companion object {
        var width = 180
        var height = 40
    }
    var item : Item? = null
    val itemChance = 0.05

    init {
        initViews(width, height, R.drawable.ic_bar)

        val genItem = Random.nextFloat() < itemChance
        if (genItem) {
            val ipX = posX + width / 2 - Item.width / 2
            val ipY = posY + height + 20
            when ((0..6).random()) {
                0,1,2,3 -> item = Beer(gameView, ipX, ipY)
                4 -> item = Shot(gameView, ipX, ipY)
                5,6 -> item = Rocket(gameView, ipX, ipY)
            }
        }
    }

    override fun removeView() {
        super.removeView()
        if (item != null) {
            item!!.removeView()
        }
    }

    open fun jump(player: Player) {
        SoundPlayer.hop.start()
        if (this::class.java == Bar::class.java) {
            Config.stats.cntBar++
        }
    }
}