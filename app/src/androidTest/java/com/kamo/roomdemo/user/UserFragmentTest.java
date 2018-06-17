package com.kamo.roomdemo.user;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import com.kamo.roomdemo.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class UserFragmentTest {

    private static final int ITEM_BELOW_THE_FOLD = 2;
    private static final int ITEM_TO_DELETE = 0;

    @Rule
    public ActivityTestRule<UserActivity> mActivityRule=new ActivityTestRule<>(UserActivity.class);

    @Test
    public void addNewUser()
    {
        Espresso.onView(withId(R.id.fab)).perform(click());
        Espresso.onView(withId(R.id.etName)).perform(typeText("Kamogelo"));
        Espresso.onView(withId(R.id.etSurname)).perform(typeText("mphahlele"));
        Espresso.onView(withId(R.id.etEmail)).perform(typeText("mphahlelejn2@gmail.com"));
        Espresso.onView(withId(R.id.etPhone)).perform(typeText("0000000000"));
        Espresso.onView(withId(R.id.etEmail)).perform(closeSoftKeyboard());
        Espresso.onView(withId(R.id.bSave))
                .perform(click());
    }


    @Test
    public void deleteUser() throws Exception {
        onView(withId(R.id.recycler_view)).perform(scrollToPosition(ITEM_TO_DELETE));
        onView(withRecyclerView(R.id.recycler_view).atPosition(ITEM_TO_DELETE)).perform(swipeRight());
        Espresso.onView(withText("REMOVE"))
                .perform(click());
    }
    @Test
    public void getRecyclerView() throws Exception {
        String itemElementText = "kamogelo";
        onView(withId(R.id.recycler_view)).perform(scrollToPosition(ITEM_BELOW_THE_FOLD));
        onView(withRecyclerView(R.id.recycler_view).atPosition( ITEM_BELOW_THE_FOLD)).check(matches(hasDescendant(withText(itemElementText))));
    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }


}