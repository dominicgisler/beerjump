package app.beerjump.activity

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import app.beerjump.R
import app.beerjump.model.Config
import app.beerjump.model.GuiElement
import kotlinx.android.synthetic.main.activity_change_character.*


class ChangeCharacterActivity : AbstractActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_character)

        var pHeight = 0.0f
        var speedUp = 0.0f
        var speed = 0.0f
        var defY = 0.0f
        var posY = 0.0f
        var selCharacter = Config.playerDrawable
        val renderRun = object : Runnable {
            override fun run() {
                if (posY + pHeight >= bar1.y) {
                    selCharacter = Config.playerDrawable
                    speed = speedUp
                    player1.y = defY
                    player2.y = defY
                    player3.y = defY
                    player4.y = defY
                    player5.y = defY
                }
                speed -= GuiElement.dpToPixels(charactersView, 0.38f)
                posY -= speed
                when (selCharacter) {
                    "player_default_%s" -> {
                        player1.y = posY
                        if (speed < 0) {
                            player1.setImageDrawable(ContextCompat.getDrawable(charactersView.context, R.drawable.player_default_down))
                        } else {
                            player1.setImageDrawable(ContextCompat.getDrawable(charactersView.context, R.drawable.player_default_up))
                        }
                    }
                    "player_gingerbread_man" -> player2.y = posY
                    "player_gnome" -> player3.y = posY
                    "player_nutcracker" -> player4.y = posY
                    "player_superhero_%s" -> {
                        player5.y = posY
                        if (speed < 0) {
                            player5.setImageDrawable(ContextCompat.getDrawable(charactersView.context, R.drawable.player_superhero_down))
                        } else {
                            player5.setImageDrawable(ContextCompat.getDrawable(charactersView.context, R.drawable.player_superhero_up))
                        }
                    }
                }
                charactersView.postDelayed(this, 15)
            }
        }
        charactersView.post {
            pHeight = GuiElement.dpToPixels(charactersView, 56.0f)
            speedUp = GuiElement.dpToPixels(charactersView, 12.0f)
            speed = speedUp
            posY = player1.y
            defY = posY
            setBarDrawable()
            charactersView.post(renderRun)
        }
        player1.setOnClickListener {
            Config.playerDrawable = "player_default_%s"
            setBarDrawable()
        }
        player2.setOnClickListener {
            Config.playerDrawable = "player_gingerbread_man"
            setBarDrawable()
        }
        player3.setOnClickListener {
            Config.playerDrawable = "player_gnome"
            setBarDrawable()
        }
        player4.setOnClickListener {
            Config.playerDrawable = "player_nutcracker"
            setBarDrawable()
        }
        player5.setOnClickListener {
            Config.playerDrawable = "player_superhero_%s"
            setBarDrawable()
        }
        buttonMenu.setOnClickListener {
            Config.save()
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }
    }

    fun setBarDrawable() {
        bar1.setImageDrawable(ContextCompat.getDrawable(charactersView.context, R.drawable.ic_disappearing_bar))
        bar2.setImageDrawable(ContextCompat.getDrawable(charactersView.context, R.drawable.ic_disappearing_bar))
        bar3.setImageDrawable(ContextCompat.getDrawable(charactersView.context, R.drawable.ic_disappearing_bar))
        bar4.setImageDrawable(ContextCompat.getDrawable(charactersView.context, R.drawable.ic_disappearing_bar))
        bar5.setImageDrawable(ContextCompat.getDrawable(charactersView.context, R.drawable.ic_disappearing_bar))
        when (Config.playerDrawable) {
            "player_default_%s" -> bar1.setImageDrawable(ContextCompat.getDrawable(charactersView.context, R.drawable.ic_bar))
            "player_gingerbread_man" -> bar2.setImageDrawable(ContextCompat.getDrawable(charactersView.context, R.drawable.ic_bar))
            "player_gnome" -> bar3.setImageDrawable(ContextCompat.getDrawable(charactersView.context, R.drawable.ic_bar))
            "player_nutcracker" -> bar4.setImageDrawable(ContextCompat.getDrawable(charactersView.context, R.drawable.ic_bar))
            "player_superhero_%s" -> bar5.setImageDrawable(ContextCompat.getDrawable(charactersView.context, R.drawable.ic_bar))
        }
    }
}