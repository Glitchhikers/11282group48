package com.example.carpoolconnect;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTests {

    @Rule
    public ActivityScenarioRule<MainActivity> rule
            = new ActivityScenarioRule<>(MainActivity.class);


    // Makes sure that the login button works
    @Test
    public void loginButtonTest() {
        onView(withId(R.id.login2))
                .perform(click());

        onView(withId(R.id.imageView))
                .check(matches(isDisplayed()));

        onView(withId(R.id.usernameLogin))
                .check(matches(isDisplayed()));

        onView(withId(R.id.passwordLogin))
                .check(matches(isDisplayed()));

    }

    // Makes sure that the sign up button works
    @Test
    public void signupButtonTest() {
        onView(withId(R.id.signup))
                .perform(click());

        onView(withId(R.id.fullname))
                .check(matches(isDisplayed()));

        onView(withId(R.id.age))
                .check(matches(isDisplayed()));

        onView(withId(R.id.email))
                .check(matches(isDisplayed()));

        onView(withId(R.id.phone))
                .check(matches(isDisplayed()));

        onView(withId(R.id.username))
                .check(matches(isDisplayed()));

        onView(withId(R.id.password))
                .check(matches(isDisplayed()));

        onView(withId(R.id.reenter_password))
                .check(matches(isDisplayed()));

        onView(withId(R.id.register))
                .check(matches(isDisplayed()));

    }
}
