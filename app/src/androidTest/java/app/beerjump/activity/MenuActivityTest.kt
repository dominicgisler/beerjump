package app.beerjump.activity

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import app.beerjump.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)

class MenuActivityTest {

    @get:Rule
    val  menuActivityScenario = ActivityScenarioRule(MenuActivity::class.java)

    @Before
    fun setUp() {

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
    fun testVisabilityHighscoreButton() {
        onView(withId(R.id.buttonHighscore)).check(matches(isDisplayed()))
    }

    @Test
    fun testVisabilitySettingsButton() {
        onView(withId(R.id.buttonSettings)).check(matches(isDisplayed()))
    }

    /* Test doesnt work beacuse layout is not loaded right. Check GameActivityTest...
    @Test
    fun testFunctionalityPlayButton(){
        onView(withId(R.id.buttonPlay)).perform(click())

        onView(withId(R.id.gameLayout)).check(matches(isDisplayed()))
    }*/

    @Test
    fun testNavigationHighscoreButton() {
        onView(withId(R.id.buttonHighscore)).perform(click())

        onView(withId(R.id.highscore)).check(matches(isDisplayed()))
    }

    @Test
    fun testNavigationSettingsButton() {
        onView(withId(R.id.buttonSettings)).perform(click())

        onView(withId(R.id.settings)).check(matches(isDisplayed()))
    }

    @Test
    fun testNavigationHighscoreButtonAndBack() {
        onView(withId(R.id.buttonHighscore)).perform(click())

        onView(withId(R.id.highscore)).check(matches(isDisplayed()))
        onView(withId(R.id.buttonMenu)).perform(click())

        onView(withId(R.id.menu)).check(matches(isDisplayed()))
   }

    @Test
    fun testNavigationSettingsButtonAndBack() {
        onView(withId(R.id.buttonSettings)).perform(click())

        onView(withId(R.id.settings)).check(matches(isDisplayed()))
        onView(withId(R.id.buttonMenu)).perform(click())

        onView(withId(R.id.menu)).check(matches(isDisplayed()))
    }
}