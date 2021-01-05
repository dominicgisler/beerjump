package app.beerjump.model

import android.view.ViewGroup
import app.beerjump.R
import kotlin.random.Random

open class Bar(gameView: ViewGroup, posX: Float, posY: Float) : GuiElement(gameView, posX, posY) {
    companion object {
        var width = 0.0f
        var height = 0.0f
        var speedUp = 0.0f

        fun calcSizes(gameView: ViewGroup) {
            width = dpToPixels(gameView, 70.0f)
            height = dpToPixels(gameView, 16.0f)
            speedUp = dpToPixels(gameView, 12.0f)
        }
    }
    var item : Item? = null
    val itemChance = 0.05

    init {
        initViews(width, height, R.drawable.ic_bar)

        val genItem = Random.nextFloat() < itemChance
        if (genItem) {
            val ipX = posX + width / 2 - Item.width / 2
            val ipY = posY + height + dpToPixels(gameView, 7.0f)
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

    open fun jump(player: Player): Boolean {
        player.speed = speedUp
        SoundPlayer.hop.start()
        if (this::class.java == Bar::class.java) {
            Config.stats.cntBar++
        }
        return true
    }
}