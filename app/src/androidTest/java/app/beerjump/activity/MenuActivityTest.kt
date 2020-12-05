package app.beerjump.activity

import androidx.lifecycle.Lifecycle
import org.junit.jupiter.api.Assertions.*
import androidx.test.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import junit.extensions.ActiveTestSuite
import org.junit.Rule
import org.junit.Test

internal class MenuActivityTest {
    @JvmField
    @get:Rule
    val menuActivityTestRule = ActivityScenarioRule<MenuActivity>(MenuActivity::class.java)

    @org.junit.jupiter.api.BeforeEach
    fun setUp() {
        val scenario = menuActivityTestRule.scenario
        scenario.moveToState(Lifecycle.State.CREATED)
    }

    @Test
    fun assertActivityLayout(){

    }

    @org.junit.jupiter.api.AfterEach
    fun tearDown() {

    }

}