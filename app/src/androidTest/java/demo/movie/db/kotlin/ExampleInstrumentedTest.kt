package demo.movie.db.kotlin

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    val STRING_TO_BE_TYPED = "Espresso"

    /**
     * Use [ActivityScenarioRule] to create and launch the activity under test.
     */
    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    @Test
    public fun greeterSaysHello() {
        onView(withId(R.id.movie_title)).perform(typeText("Avatar"))
        onView(withId(R.id.movie_layout)).perform(click())
        onView(withText("Avatar")).check(matches(isDisplayed()))
    }

}