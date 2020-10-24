package app.beerjump.model

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import app.beerjump.R
import kotlin.random.Random

class Game(val gameView: ViewGroup) {
    val speedUp = 30
    val sectionHeight = 250
    val sectionPadding = 30
    var height = 0
    var sections = 0
    var beerCnt = 0
    val promillePerBeer = 0.2

    val player: Player = Player()
    val bars: ArrayList<Bar> = ArrayList()
    val beers: ArrayList<Beer> = ArrayList()

    fun generate() {
        for (i in 0..gameView.height / sectionHeight) {
            addBarSection(i * sectionHeight)
        }
        player.posX = gameView.width / 2 - player.width / 2
        player.posY = 0
    }

    private fun addBarSection(startY: Int) {
        val genBeer = (0..20).random()
        val numBars = (1..3).random()
        val secWidth = gameView.width / numBars
        for (j in 0..numBars) {
            val bar = Bar()
            val minX = secWidth * j + sectionPadding
            val maxX = secWidth * (j + 1) - bar.width - sectionPadding
            val minY = startY + sectionPadding
            val maxY = startY + sectionHeight - sectionPadding
            bar.posX = ((Random.nextFloat() * (maxX - minX)) + minX).toInt()
            bar.posY = ((Random.nextFloat() * (maxY - minY)) + minY).toInt()
            bars.add(bar)
            if (genBeer == j) {
                val beer = Beer()
                beer.posX = bar.posX + bar.width / 2 - beer.width / 2
                beer.posY = bar.posY + bar.height + 20
                beers.add(beer)
            }
        }
        sections++
    }

    fun step(): Boolean {
        player.speed--

        if (player.posX < -player.width) {
            player.posX = gameView.width
        } else if (player.posX > gameView.width) {
            player.posX = -player.width
        }

        // detect bar contact if going down
        if (player.speed < 0) {
            for (bar in bars) {
                if ((player.posX + player.width / 2) in bar.posX..(bar.posX + bar.width) && (player.posY) in bar.posY..(bar.posY + bar.height)) {
                    player.speed = speedUp
                }
            }
        }
        val iter = beers.iterator()
        while (iter.hasNext()) {
            val beer = iter.next()
            if ((player.posX + player.width / 2) in beer.posX..(beer.posX + beer.width) && player.posY in (beer.posY - player.height)..(beer.posY + beer.height)) {
                iter.remove()
                beerCnt++
            }
        }

        if (player.posY > (gameView.height / 2 + height) && player.speed > 0) {
            height += player.speed
        }

        if (sections * sectionHeight < height + gameView.height) {
            addBarSection(sections * sectionHeight)
        }

        if (player.posY - height + sectionHeight < 0) {
            return false
        } else {
            player.posX += player.direction
            player.posY += player.speed
            return true
        }
    }

    fun render() {
        gameView.removeAllViews()

        for (bar in bars) {
            val posY = (gameView.height - bar.posY - bar.height).toFloat() + height
            if (posY < -sectionHeight || posY > gameView.height + sectionHeight) {
                continue
            }
            val viewBar = ImageView(gameView.context)
            gameView.addView(viewBar)
            viewBar.layoutParams.width = bar.width
            viewBar.layoutParams.height = bar.height
            viewBar.x = bar.posX.toFloat()
            viewBar.y = posY
            viewBar.setImageDrawable(ContextCompat.getDrawable(gameView.context, R.drawable.ic_bar))
        }

        for (beer in beers) {
            val posY = (gameView.height - beer.posY - beer.height).toFloat() + height
            if (posY < -sectionHeight || posY > gameView.height + sectionHeight) {
                continue
            }
            val viewBar = ImageView(gameView.context)
            gameView.addView(viewBar)
            viewBar.layoutParams.width = beer.width
            viewBar.layoutParams.height = beer.height
            viewBar.x = beer.posX.toFloat()
            viewBar.y = posY
            viewBar.setImageDrawable(ContextCompat.getDrawable(gameView.context, R.drawable.ic_beer))
        }

        val playerView = ImageView(gameView.context)
        gameView.addView(playerView)
        if (player.speed > 0) {
            playerView.setImageDrawable(ContextCompat.getDrawable(gameView.context, R.drawable.ic_player_up))
        } else {
            playerView.setImageDrawable(ContextCompat.getDrawable(gameView.context, R.drawable.ic_player_down))
        }

        playerView.layoutParams.width = player.width
        playerView.layoutParams.height = player.height
        playerView.x = player.posX.toFloat()
        playerView.y = (gameView.height - player.posY - player.height).toFloat() + height

        val heightLabel = TextView(gameView.context)
        heightLabel.x = sectionPadding.toFloat()
        heightLabel.y = sectionPadding.toFloat()
        heightLabel.textSize = 18f
        heightLabel.text = "Aktuelle Höhe"
        val heightValue = TextView(gameView.context)
        heightValue.x = sectionPadding.toFloat()
        heightValue.y = heightLabel.y + 40
        heightValue.textSize = 32f
        heightValue.text = height.toString()
        gameView.addView(heightLabel)
        gameView.addView(heightValue)

        val beerLabel = TextView(gameView.context)
        beerLabel.x = (sectionPadding + 350).toFloat()
        beerLabel.y = (sectionPadding).toFloat()
        beerLabel.textSize = 18f
        beerLabel.text = "Promille"
        val beerValue = TextView(gameView.context)
        beerValue.x = (sectionPadding + 350).toFloat()
        beerValue.y = heightLabel.y + 40
        beerValue.textSize = 32f
        beerValue.text = (beerCnt * promillePerBeer).toString()
        gameView.addView(beerLabel)
        gameView.addView(beerValue)
    }

    fun setDirection(direction: Int) {
        player.direction = direction
    }

}