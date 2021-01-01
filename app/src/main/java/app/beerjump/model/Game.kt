package app.beerjump.model

import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_game.view.*

class Game(val gameLayout: ViewGroup, val highscore: Int) {
    var height = 0
    val promilleStep = -0.0001
    val difficultyStep = 20000

    val sections: ArrayList<Section> = ArrayList()
    lateinit var player: Player
    lateinit var tunnel: Tunnel

    var gameView = gameLayout.gameView
    var statsView = gameLayout.statsView

    fun generate() {
        // calculate sizes from dp to pixels
        Section.calcSizes(gameView)
        Bar.calcSizes(gameView)
        Player.calcSizes(gameView)
        Item.calcSizes(gameView)

        player = Player(gameLayout.gameView, 0.0f, 0.0f)
        Section.num = 0
        for (i in 0..(gameView.height / Section.height).toInt()) {
            addBarSection(i * Section.height)
        }
        player.posX = (gameView.width / 2 - Player.width / 2)
        player.posY = 0.0f
        statsView.scoreHighscore.text = highscore.toString()
        tunnel = Tunnel(gameLayout.gameView, 0.0f, 0.0f)
        SoundPlayer.init(gameView)
    }

    private fun addBarSection(startY: Float) {
        val maxBars = 5 - (height/difficultyStep).toInt()
        sections.add(Section(gameView, startY, maxBars))
    }

    fun step(): Boolean {
        player.speed -= GuiElement.dpToPixels(gameView, 0.38f)
        if (player.promille > 0) {
            player.promille += promilleStep
        }

        if (player.posX < -Player.width) {
            player.posX = gameView.width.toFloat()
        } else if (player.posX > gameView.width) {
            player.posX = -Player.width
        }

        for (sec in sections) {
            for (bar in sec.bars) {
                val posY = (gameView.height - bar.posY - Bar.height) + height
                if (posY > gameView.height) {
                    continue
                }
                if (player.speed < 0) {
                    if ((player.posX + Player.width / 2) in bar.posX..(bar.posX + Bar.width) && (player.posY) in bar.posY..(bar.posY + Bar.height)) {
                        bar.jump(player)
                    }
                }
                if (bar.item != null) {
                    val item = bar.item!!
                    if ((player.posX + Player.width / 2) in item.posX..(item.posX + Item.width) && player.posY in (item.posY - Player.height)..(item.posY + Item.height)) {
                        item.removeView()
                        item.pickup(player)
                        bar.item = null
                    }
                }
            }
        }


        if (player.posY > (gameView.height / 2 + height) && player.speed > 0) {
            height += player.speed.toInt()
            player.score += (player.speed * (1 + player.promille)).toInt()
        }

        if (sections.size * Section.height < height + gameView.height) {
            addBarSection(sections.size * Section.height)
        }

        if (player.posY - height + Section.height < 0) {
            return false
        } else {
            player.posX += player.direction
            player.posY += player.speed.toInt()
            return true
        }
    }

    fun render() {
        for (sec in sections) {
            for (bar in sec.bars) {
                var posY = (gameView.height - bar.posY - Bar.height) + height
                if (posY < -Section.height || posY > gameView.height + Section.height) {
                    if (posY > gameView.height + Section.height) {
                        bar.removeView()
                    }
                    continue
                }
                bar.updateView(player)
                bar.view.y = (posY - GuiElement.dpToPixels(gameView, player.promille.toFloat()))
                bar.view2.y = (posY + GuiElement.dpToPixels(gameView, player.promille.toFloat()))
                if (bar.item != null) {
                    val item = bar.item!!
                    posY = (gameView.height - item.posY - Item.height) + height
                    item.view.y = (posY - GuiElement.dpToPixels(gameView, player.promille.toFloat()))
                    item.view2.y = (posY + GuiElement.dpToPixels(gameView, player.promille.toFloat()))
                    item.updateView(player)
                }
            }
        }

        player.updateView()
        player.view.y = (gameView.height - player.posY - Player.height) + height

        statsView.scorePromille.text = String.format("%.2f‰", player.promille)
        statsView.scoreScore.text = player.score.toString()

        tunnel.updateView(player)
    }
}