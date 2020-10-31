package app.beerjump.model

import android.media.MediaPlayer
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import app.beerjump.R
import kotlinx.android.synthetic.main.activity_game.view.*

class Game(val gameLayout: ViewGroup) {
    val speedUp = 30
    val rocketSpeed = 120
    var height = 0
    val promillePerBeer = 0.2
    val promillePerShot = 0.5
    val promilleStep = -0.0001
    val difficultyStep = 20000

    val player: Player = Player(gameLayout.gameView, 0, 0)
    val sections: ArrayList<Section> = ArrayList()
    val beers: ArrayList<Beer> = ArrayList()
    val shots: ArrayList<Shot> = ArrayList()
    val rockets: ArrayList<Rocket> = ArrayList()

    var gameView = gameLayout.gameView
    var statsView = gameLayout.statsView

    val hop = MediaPlayer.create(gameView.context, R.raw.hop)
    val beerSound = MediaPlayer.create(gameView.context, R.raw.beer)

    fun generate() {
        for (i in 0..gameView.height / Section.height) {
            addBarSection(i * Section.height)
        }
        player.posX = gameView.width / 2 - Player.width / 2
        player.posY = 0
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

        // detect bar contact if going down
        if (player.speed < 0) {
            for (sec in sections) {
                for (bar in sec.bars) {
                    if ((player.posX + Player.width / 2) in bar.posX..(bar.posX + Bar.width) && (player.posY) in bar.posY..(bar.posY + Bar.height)) {
                        player.speed = speedUp
                        hop.start()
                    }
                }
            }
        }
        val iterBeer = beers.iterator()
        while (iterBeer.hasNext()) {
            val beer = iterBeer.next()
            if ((player.posX + Player.width / 2) in beer.posX..(beer.posX + Beer.width) && player.posY in (beer.posY - Player.height)..(beer.posY + Beer.height)) {
                iterBeer.remove()
                player.promille += promillePerBeer
                beerSound.start()
            }
        }
        val iterShot = shots.iterator()
        while (iterShot.hasNext()) {
            val shot = iterShot.next()
            if ((player.posX + Player.width / 2) in shot.posX..(shot.posX + Shot.width) && player.posY in (shot.posY - Player.height)..(shot.posY + Shot.height)) {
                iterShot.remove()
                player.promille += promillePerShot
            }
        }
        val iterRocket = rockets.iterator()
        while (iterRocket.hasNext()) {
            val rocket = iterRocket.next()
            if ((player.posX + Player.width / 2) in rocket.posX..(rocket.posX + Rocket.width) && player.posY in (rocket.posY - Player.height)..(rocket.posY + Rocket.height)) {
                iterRocket.remove()
                player.speed = rocketSpeed
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
                val posY = (gameView.height - bar.posY - Bar.height).toFloat() + height
                if (posY < -Section.height || posY > gameView.height + Section.height) {
                    continue
                }
                bar.view.y = posY
            }
        }

        for (beer in beers) {
            val posY = (gameView.height - beer.posY - Beer.height).toFloat() + height
            if (posY < -Section.height || posY > gameView.height + Section.height) {
                continue
            }
            val viewBar = ImageView(gameView.context)
            gameView.addView(viewBar)
            viewBar.layoutParams.width = Beer.width
            viewBar.layoutParams.height = Beer.height
            viewBar.x = beer.posX.toFloat()
            viewBar.y = posY
            viewBar.setImageDrawable(ContextCompat.getDrawable(gameView.context, R.drawable.ic_beer))
        }

        for (shot in shots) {
            val posY = (gameView.height - shot.posY - Shot.height).toFloat() + height
            if (posY < -Section.height || posY > gameView.height + Section.height) {
                continue
            }
            val viewBar = ImageView(gameView.context)
            gameView.addView(viewBar)
            viewBar.layoutParams.width = Shot.width
            viewBar.layoutParams.height = Shot.height
            viewBar.x = shot.posX.toFloat()
            viewBar.y = posY
            viewBar.setImageDrawable(ContextCompat.getDrawable(gameView.context, R.drawable.ic_shot))
        }

        for (rocket in rockets) {
            val posY = (gameView.height - rocket.posY - Rocket.height).toFloat() + height
            if (posY < -Section.height || posY > gameView.height + Section.height) {
                continue
            }
            val viewBar = ImageView(gameView.context)
            gameView.addView(viewBar)
            viewBar.layoutParams.width = Rocket.width
            viewBar.layoutParams.height = Rocket.height
            viewBar.x = rocket.posX.toFloat()
            viewBar.y = posY
            viewBar.setImageDrawable(ContextCompat.getDrawable(gameView.context, R.drawable.ic_rocket))
        }

        if (player.speed > 0) {
            player.view.setImageDrawable(ContextCompat.getDrawable(gameView.context, R.drawable.ic_player_up))
        } else {
            player.view.setImageDrawable(ContextCompat.getDrawable(gameView.context, R.drawable.ic_player_down))
        }
        player.view.x = player.posX.toFloat()
        player.view.y = (gameView.height - player.posY - Player.height).toFloat() + height

        statsView.scoreHeight.text = height.toString()
        statsView.scorePromille.text = String.format("%.2f‰", player.promille)
        statsView.scoreScore.text = player.score.toString()
    }

    fun setDirection(direction: Int) {
        player.direction = direction
    }

}