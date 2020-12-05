package app.beerjump.model

import android.media.MediaPlayer
import android.view.ViewGroup
import app.beerjump.R
import kotlinx.android.synthetic.main.activity_game.view.*

class Game(val gameLayout: ViewGroup, val highscore: Int) {
    val speedUp = 30
    var height = 0
    val promilleStep = -0.0001
    val difficultyStep = 20000

    val player: Player = Player(gameLayout.gameView, 0, 0)
    val sections: ArrayList<Section> = ArrayList()
    lateinit var tunnel: Tunnel

    var gameView = gameLayout.gameView
    var statsView = gameLayout.statsView

    fun generate() {
        for (i in 0..gameView.height / Section.height) {
            addBarSection(i * Section.height)
        }
        player.posX = gameView.width / 2 - Player.width / 2
        player.posY = 0
        statsView.scoreHighscore.text = highscore.toString()
        tunnel = Tunnel(gameLayout.gameView, 0, 0)
        SoundPlayer.init(gameView)
    }

    private fun addBarSection(startY: Int) {
        val maxBars = 5 - height/difficultyStep
        sections.add(Section(gameView, startY, maxBars))
    }

    fun step(): Boolean {
        player.speed--
        if (player.promille > 0) {
            player.promille += promilleStep
        }

        if (player.posX < -Player.width) {
            player.posX = gameView.width
        } else if (player.posX > gameView.width) {
            player.posX = -Player.width
        }

        for (sec in sections) {
            for (bar in sec.bars) {
                if (player.speed < 0) {
                    if ((player.posX + Player.width / 2) in bar.posX..(bar.posX + Bar.width) && (player.posY) in bar.posY..(bar.posY + Bar.height)) {
                        player.speed = speedUp
                        SoundPlayer.hop.start()
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
            height += player.speed
            player.score += (player.speed * (1 + player.promille)).toInt()
        }

        if (sections.size * Section.height < height + gameView.height) {
            addBarSection(sections.size * Section.height)
        }

        if (player.posY - height + Section.height < 0) {
            return false
        } else {
            player.posX += player.direction
            player.posY += player.speed
            return true
        }
    }

    fun render() {
        for (sec in sections) {
            for (bar in sec.bars) {
                var posY = (gameView.height - bar.posY - Bar.height).toFloat() + height
                if (posY < -Section.height || posY > gameView.height + Section.height) {
                    bar.removeView()
                    continue
                }
                bar.updateView(player)
                bar.view.y = (posY - player.promille * 2).toFloat()
                bar.view2.y = (posY + player.promille * 2).toFloat()
                if (bar.item != null) {
                    val item = bar.item!!
                    posY = (gameView.height - item.posY - Item.height).toFloat() + height
                    item.view.y = (posY - player.promille * 2).toFloat()
                    item.view2.y = (posY + player.promille * 2).toFloat()
                    item.updateView(player)
                }
            }
        }

        player.updateView()
        player.view.y = (gameView.height - player.posY - Player.height).toFloat() + height

        statsView.scorePromille.text = String.format("%.2f‰", player.promille)
        statsView.scoreScore.text = player.score.toString()

        tunnel.updateView(player)
    }
}