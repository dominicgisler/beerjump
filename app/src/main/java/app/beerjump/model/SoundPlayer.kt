package app.beerjump.model

import android.media.MediaPlayer
import android.view.ViewGroup
import app.beerjump.R

object SoundPlayer {
    lateinit var hop : MediaPlayer
    lateinit var beer : MediaPlayer
    lateinit var shot : MediaPlayer
    lateinit var rocket : MediaPlayer

    fun init(gameView: ViewGroup) {
        shot = MediaPlayer.create(gameView.context, R.raw.shot)
        rocket = MediaPlayer.create(gameView.context, R.raw.rocket)
        hop = MediaPlayer.create(gameView.context, R.raw.hop)
        beer = MediaPlayer.create(gameView.context, R.raw.beer)
    }
}