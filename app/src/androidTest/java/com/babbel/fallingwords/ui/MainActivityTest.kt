package com.babbel.fallingwords.ui


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.babbel.fallingwords.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testStartQuizButtonDisplayed() {
        // Test StartQuiz button is displayed
        val startButton = onView(
            allOf(
                withId(R.id.button_start_quiz), withText(R.string.start_quiz),
                withParent(withParent(withId(R.id.nav_host_fragment_content_main))),
                isDisplayed()
            )
        )
        startButton.check(matches(isDisplayed()))
        startButton.perform(click())

        // Test Next button is displayed
        onView(
            allOf(
                withId(R.id.button_start_quiz), withText(R.string.button_text_next),
                withParent(withParent(withId(R.id.nav_host_fragment_content_main))),
                isDisplayed()
            )
        )
    }

    @Test
    fun testCorrectWrongButtonsDisplayed() {
        // Test CORRECT button is displayed
        onView(
            allOf(
                withId(R.id.button_correct), withText(R.string.correct),
                withParent(withParent(withId(R.id.nav_host_fragment_content_main))),
                isDisplayed()
            )
        )

        // Test Wrong button is displayed
        onView(
            allOf(
                withId(R.id.button_wrong), withText(R.string.wrong),
                withParent(withParent(withId(R.id.nav_host_fragment_content_main))), isDisplayed()
            )
        )
    }

    @Test
    fun testQATextsDisplayed() {
        onView(allOf(withId(R.id.textview_answer), isDisplayed()))
        onView(allOf(withId(R.id.textview_question), isDisplayed()))
        onView(allOf(withId(R.id.button_wrong), isNotClickable()))
        onView(allOf(withId(R.id.button_correct), isNotClickable()))
        onView(allOf(withId(R.id.button_start_quiz), withText(R.string.next) ,isNotClickable()))
    }

}
