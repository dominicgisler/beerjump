package app.beerjump.activity

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import app.beerjump.R
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class GameActivityTest {

    @get:Rule
    val  gameActivityScenario = ActivityScenarioRule(GameActivity::class.java)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testLayout() {
        Espresso.onView(ViewMatchers.withId(R.id.gameLayout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
       fun testTextHighscore() {
        Espresso.onView(ViewMatchers.withId(R.id.textHighscore))
            .check(ViewAssertions.matches(ViewMatchers.withText("Highscore")))
    }

    @Test
    fun testTextPromille() {
        Espresso.onView(ViewMatchers.withId(R.id.textPromille))
            .check(ViewAssertions.matches(ViewMatchers.withText("Promille")))
    }

    @Test
    fun testTextScore() {
        Espresso.onView(ViewMatchers.withId(R.id.textScore))
            .check(ViewAssertions.matches(ViewMatchers.withText("Score")))
    }

    @Test
    fun testVisabilityScoreHighscore() {
        Espresso.onView(ViewMatchers.withId(R.id.scoreHighscore))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testVisabilityScorePromille() {
        Espresso.onView(ViewMatchers.withId(R.id.scorePromille))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testVisabilityScoreScore() {
        Espresso.onView(ViewMatchers.withId(R.id.scoreScore))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testVisabilitybuttonPause() {
        Espresso.onView(ViewMatchers.withId(R.id.buttonPause))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}