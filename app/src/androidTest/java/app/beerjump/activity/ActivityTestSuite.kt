package app.beerjump.activity

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    SplashActivityTest::class,
    MenuActivityTest::class,
    HighscoreActivityTest::class,
    SettingsActivityTest::class,
    GameScoreActivityTest::class,
    GameActivityTest::class
)
class ActivityTestSuite {
}