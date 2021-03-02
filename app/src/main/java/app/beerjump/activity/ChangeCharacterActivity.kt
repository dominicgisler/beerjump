package app.beerjump.activity

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import app.beerjump.R
import app.beerjump.adapter.CharacterAdapter
import app.beerjump.model.Config
import app.beerjump.model.GuiElement
import kotlinx.android.synthetic.main.activity_change_character.*
import kotlinx.android.synthetic.main.activity_change_character.buttonMenu

class ChangeCharacterActivity : AbstractActivity() {
    var chars = ArrayList<String>()
    lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_character)

        chars.add("player_default_%s")
        chars.add("player_superhero_%s")
        chars.add("player_unicorn_%s")
        chars.add("player_astronaut")
        chars.add("player_worker")
        chars.add("player_gingerbread_man")
        chars.add("player_gnome")
        chars.add("player_nutcracker")
        chars.add("player_king")
        chars.add("player_queen")
        chars.add("player_prince")
        chars.add("player_princess")
        chars.add("player_witch")
        chars.add("player_wizard")
        chars.add("player_ghost")
        chars.add("player_skeleton")
        chars.add("player_devil")
        chars.add("player_robot")
        chars.add("player_android")

        adapter = CharacterAdapter(chars, baseContext)
        charactersView.adapter = adapter

        var pHeight = 0.0f
        var speedUp = 0.0f
        var speed = 0.0f
        var posY = 0.0f
        val renderRun = object : Runnable {
            override fun run() {
                if (posY + pHeight - speed >= barPreview.y) {
                    speed = speedUp
                }
                speed -= GuiElement.dpToPixels(charactersView, 0.38f)
                posY -= speed
                playerPreview.y = posY
                if (speed < 0) {
                    playerPreview.setImageDrawable(
                        ContextCompat.getDrawable(
                            baseContext,
                            baseContext.resources.getIdentifier(
                                Config.playerDrawable.format("down"),
                                "drawable",
                                baseContext.packageName
                            )
                        )
                    )
                } else {
                    playerPreview.setImageDrawable(
                        ContextCompat.getDrawable(
                            baseContext,
                            baseContext.resources.getIdentifier(
                                Config.playerDrawable.format("up"),
                                "drawable",
                                baseContext.packageName
                            )
                        )
                    )
                }
                charactersView.postDelayed(this, 15)
            }
        }
        charactersView.post {
            pHeight = GuiElement.dpToPixels(charactersView, 56.0f)
            speedUp = GuiElement.dpToPixels(charactersView, 12.0f)
            speed = speedUp
            posY = playerPreview.y
            charactersView.post(renderRun)
        }
        charactersView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val char = parent.getItemAtPosition(position) as String
                Config.playerDrawable = char
            }
        buttonMenu.setOnClickListener {
            Config.save()
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }
    }
}