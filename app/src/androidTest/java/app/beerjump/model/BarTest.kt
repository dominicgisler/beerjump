package app.beerjump.model

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import android.view.ViewGroup


class BarTest {
    val bars = ArrayList<Bar>()
    val gameView =  ViewGroup()


    @org.junit.jupiter.api.BeforeEach
    fun setUp() {
        bars.add(Bar(gameView, 100, 200))
        bars.add(Bar(gameView, 100, 300))
        bars.add(Bar(gameView, 100, 400))
        bars.add(Bar(gameView, 100, 500))
    }

    @org.junit.jupiter.api.AfterEach
    fun tearDown() {
        bars.clear()
    }


    @org.junit.jupiter.api.Test
    fun removeView() {

    }
}