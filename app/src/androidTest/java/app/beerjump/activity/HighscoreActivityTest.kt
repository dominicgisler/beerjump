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

class HighscoreActivityTest {

    @get:Rule
    val  highscoreActivityScenario = ActivityScenarioRule(HighscoreActivity::class.java)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testLayout() {
        Espresso.onView(ViewMatchers.withId(R.id.highscore))
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
            .check(ViewAssertions.matches(ViewMatchers.withText("Highscore")))
    }

    @Test
    fun testTextName() {
        Espresso.onView(ViewMatchers.withId(R.id.textUsername))
            .check(ViewAssertions.matches(ViewMatchers.withText("Name")))
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
    fun testVisabilityHighscoreList(){
        Espresso.onView(ViewMatchers.withId(R.id.highscoreList))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testVisabilityMenuButton() {
        Espresso.onView(ViewMatchers.withId(R.id.buttonMenu))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testNavigationMenuButton() {

        Espresso.onView(ViewMatchers.withId(R.id.buttonMenu))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.buttonMenu)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.menu))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


}
