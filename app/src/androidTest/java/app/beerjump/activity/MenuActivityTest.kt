package app.beerjump.activity

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import app.beerjump.R
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)

class MenuActivityTest {

    @Before
    fun setUp() {
        val menuActivityScenario = ActivityScenario.launch(MenuActivity::class.java)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun testLayout() {
        onView(withId(R.id.menu)).check(matches(isDisplayed()))
    }

    @Test
    fun testImg() {
        onView(withId(R.id.imageView)).check(matches(isDisplayed()))
    }

    @Test
    fun testTitle() {
        onView(withId(R.id.textTitle)).check(matches(withText("Beer Jump")))
    }

    @Test
    fun testVisabilityPlayButton() {
        onView(withId(R.id.buttonPlay)).check(matches(isDisplayed()))
    }

    @Test
    fun testVisabilitHighscoreButton() {
        onView(withId(R.id.buttonHighscore)).check(matches(isDisplayed()))
    }

    @Test
    fun testVisabilitSettingsButton() {
        onView(withId(R.id.buttonSettings)).check(matches(isDisplayed()))
    }

    @Test
    fun testFunctionalityPlayButton(){
    }
}