package app.beerjump.activity

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import app.beerjump.R
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class SplashActivityTest {

    @get:Rule
    val  splashActivityScenario = ActivityScenarioRule(SplashActivity::class.java)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testLayout() {
        Espresso.onView(ViewMatchers.withId(R.id.splash))
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
            .check(ViewAssertions.matches(ViewMatchers.withText("Beer Jump")))
    }

    @Test
    fun testProgressBar() {
        Espresso.onView(ViewMatchers.withId(R.id.progressBar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}