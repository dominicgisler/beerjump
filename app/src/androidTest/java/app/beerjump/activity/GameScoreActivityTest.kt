package app.beerjump.activity

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import app.beerjump.R
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class GameScoreActivityTest {

    @get:Rule
    val  gameScoreActivityScenario = ActivityScenarioRule(GameScoreActivity::class.java)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testLayout() {
        Espresso.onView(ViewMatchers.withId(R.id.gameScore))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testImg() {
        Espresso.onView(ViewMatchers.withId(R.id.imageView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testTitle() {
        Espresso.onView(ViewMatchers.withId(R.id.textTitle))
            .check(ViewAssertions.matches(ViewMatchers.withText("Game Score")))
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
    fun testVisabilityTextfieldName() {
        Espresso.onView(ViewMatchers.withId(R.id.name))
            .check(ViewAssertions.matches(ViewMatchers.withHint("Name")))
    }

    @Test
    fun testVisabilitybuttonOk() {
        Espresso.onView(ViewMatchers.withId(R.id.ok))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testNavigationbuttonOk() {
        Espresso.onView(ViewMatchers.withId(R.id.ok))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.ok)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.highscore))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }
}