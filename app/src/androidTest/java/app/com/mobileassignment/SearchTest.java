package app.com.mobileassignment;


import android.content.Intent;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import app.com.mobileassignment.matchers.Matchers;
import app.com.mobileassignment.views.MainActivity;
import app.com.mobileassignment.views.MapActivity;

import static android.app.Activity.RESULT_OK;
import static android.app.Instrumentation.ActivityResult;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.registerIdlingResources;
import static android.support.test.espresso.Espresso.unregisterIdlingResources;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static app.com.mobileassignment.matchers.Matchers.matchToolbarTitle;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void initialScreenContainsAllElements() {
        matchToolbarTitle("Mobile Assignment").check(matches(isDisplayed()));
        onView(withId(R.id.search)).check(matches(isDisplayed()));
        onView(withId(R.id.citiesList)).check(matches(isDisplayed()));
    }

    @Test
    public void searchByFullName() {
        onView(withId(R.id.citiesList)).check(matches(isDisplayed()));
        onView(withId(R.id.search)).perform(replaceText("Amsterdam"));

        CountingIdlingResource mainActivityIdlingResource = activityRule.getActivity().dataLoadingIdlingResource;
        registerIdlingResources(mainActivityIdlingResource);

        onView(withId(R.id.citiesList)).check(matches(Matchers.withListSize(4)));

        unregisterIdlingResources(activityRule.getActivity().dataLoadingIdlingResource);
    }

    @Test
    public void openMapOnItemClick() {
        onView(withId(R.id.citiesList)).check(matches(isDisplayed()));
        onView(withId(R.id.search)).perform(replaceText("Amsterdam"));

        ActivityResult result = new ActivityResult(RESULT_OK, null);
        Matcher<Intent> hasComponent = hasComponent(MapActivity.class.getName());
        intending(hasComponent).respondWith(result);
        onData(anything()).inAdapterView(withId(R.id.citiesList)).atPosition(0).perform(click());

        intended(hasComponent(MapActivity.class.getName()));
    }
}