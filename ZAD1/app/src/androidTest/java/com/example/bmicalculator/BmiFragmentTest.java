package com.example.bmicalculator;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BmiFragmentTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testBmiCalculation() {
        onView(withId(R.id.navigation_bmi)).perform(click());
        
        onView(withId(R.id.editTextWeight)).perform(typeText("70"), closeSoftKeyboard());
        onView(withId(R.id.editTextHeight)).perform(typeText("175"), closeSoftKeyboard());
        
        onView(withId(R.id.buttonCalculateBmi)).perform(click());
        
        onView(withId(R.id.textViewBmiResult)).check(matches(isDisplayed()));
        
        onView(withId(R.id.textViewBmiStatus)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewBmiStatus)).check(matches(withText("Optimum")));
    }
    
    @Test
    public void testEmptyInput() {
        onView(withId(R.id.navigation_bmi)).perform(click());
        
        onView(withId(R.id.buttonCalculateBmi)).perform(click());
    }
} 