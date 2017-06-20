package explorer.city.com.cityexplorer

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import explorer.city.com.cityexplorer.view.activity.MainActivity
import org.hamcrest.core.IsAnything.anything
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */

@LargeTest
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTestKotlin {

    @Rule @JvmField
    val testRule: ActivityTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        onView(withId(R.id.searchList)).check(matches(isDisplayed()))

        onData(anything()).inAdapterView(withId(R.id.searchList))
    }
}
