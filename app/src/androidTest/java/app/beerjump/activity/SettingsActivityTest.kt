package app.beerjump.activity

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import app.beerjump.R
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)


class SettingsActivityTest {

    @get:Rule
    val  settingsActivityScenario = ActivityScenarioRule(SettingsActivity::class.java)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testLayout() {
        Espresso.onView(ViewMatchers.withId(R.id.settings))
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
            .check(ViewAssertions.matches(ViewMatchers.withText("Einstellungen")))
    }

    @Test
    fun testVisabilityHighscoreResetButton() {
        Espresso.onView(ViewMatchers.withId(R.id.buttonResetHighscore))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testVisabilityInput() {
        Espresso.onView(ViewMatchers.withId(R.id.inputTypeText))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testVisabilitybuttonInputToggle() {
        Espresso.onView(ViewMatchers.withId(R.id.buttonInputToggle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testVisabilityMenuButton() {
        Espresso.onView(ViewMatchers.withId(R.id.buttonMenu))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}