package app.beerjump.activity

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import app.beerjump.R
import kotlinx.android.synthetic.main.activity_menu.view.*
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)

class MenuActivityTest {

    @Before
    fun setUp() {
        val menuActivityScenario = ActivityScenario.launch(MenuActivity::class.java)

    @After
    fun tearDown() {
    }

    @Test
    fun test1() {
        onView(withId(R.id.menu)).check(matches(isDisplayed()))
    }
    }
}