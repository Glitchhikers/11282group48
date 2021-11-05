package com.example.carpoolconnect;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
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
public class LoginScreenTests {
    private String username = "test";
    private String password = "test";

    @Rule
    public ActivityScenarioRule<LoginScreen> rule
            = new ActivityScenarioRule<>(LoginScreen.class);

    // Makes sure that all UI elements on screen are displayed
    @Test
    public void checkIfUIElementsDisplayed() {
        onView(withId(R.id.imageView))
                .check(matches(isDisplayed()));

        onView(withId(R.id.usernameLogin))
                .check(matches(isDisplayed()));

        onView(withId(R.id.passwordLogin))
                .check(matches(isDisplayed()));
    }

    // Makes sure that you can login correctly

    /*
    @Test
    public void loginTest() {
        onView(withId(R.id.usernameLogin))
                .perform(typeText(username));

        onView(withId(R.id.passwordLogin))
                .perform(typeText(password));

        onView(withId(R.id.login2))
                .perform(click());

        onView(withId(R.id.drive))
                .check(matches(isDisplayed()));

        onView(withId(R.id.ride))
                .check(matches(isDisplayed()));

        onView(withId(R.id.posts))
                .check(matches(isDisplayed()));

    }
    */


}
