package com.reactivemobile.app.presentation.ui.rates

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.reactivemobile.app.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class RatesActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(RatesActivity::class.java)

    @Test
    fun ratesActivityTest() {
        val appCompatEditText = onView(
            allOf(
                withId(R.id.exchange_rate), withText("1.0"),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("10.0"))

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.exchange_rate), withText("10.0"),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(closeSoftKeyboard())

        val editText = onView(
            allOf(
                withId(R.id.exchange_rate), withText("20"),
                isDisplayed()
            )
        )
        editText.check(matches(withText("20")))

        val constraintLayout = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.recycler_view),
                        childAtPosition(
                            withId(R.id.main_view),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        constraintLayout.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.currency_code), withText("AUD"),
                isDisplayed()
            )
        )
        textView.check(matches(withText("AUD")))

        val constraintLayout2 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.recycler_view),
                        childAtPosition(
                            withId(R.id.main_view),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        constraintLayout2.perform(click())

        val textView2 = onView(
            allOf(
                withId(R.id.currency_code), withText("BGN"),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("BGN")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
