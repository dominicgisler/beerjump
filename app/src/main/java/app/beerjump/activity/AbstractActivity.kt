package app.beerjump.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import app.beerjump.R
import app.beerjump.model.Config

abstract class AbstractActivity : AppCompatActivity() {
    lateinit var config : Config

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setDecorView()
        config = Config(this.getSharedPreferences("config", Context.MODE_PRIVATE), baseContext)
        config.highscoreList.scores.sortByDescending { it.score }
    }

    override fun onResume() {
        super.onResume()
        setDecorView()
    }

    fun setDecorView() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}