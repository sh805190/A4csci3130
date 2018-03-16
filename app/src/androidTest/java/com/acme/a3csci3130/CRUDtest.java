package com.acme.a3csci3130;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ListView;
import com.google.firebase.database.FirebaseDatabase;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.*;


/**
 * The type Cru dtest.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CRUDtest {
    @Rule
    public ActivityTestRule<MainActivity> CRUDtestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private String createname = "John";
    private String updatename = "Bob";
    private String createemail = "12345@gmail.com";
    private String createnumber = "902123456";
    private String createbusiness = "Fisher";
    private String createaddress = "Hali Street";
    private String createprovince = "NS";
    @Before
    public void setUp() throws Exception{}

    /**
     * Create test.
     */
    @Test
    public void ACreateTest (){
        onView(withId(R.id.submitButton)).perform(click());

        onView(withId(R.id.name)).perform(typeText(createname));
        onView(withId(R.id.email)).perform(typeText(createemail));
        onView(withId(R.id.business)).perform(typeText(createbusiness));
        onView(withId(R.id.province)).perform(typeText(createprovince));
        onView(withId(R.id.address)).perform(typeText(createaddress));
        onView(withId(R.id.number)).perform(typeText(createnumber));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.submitButton)).perform(click());
        int listLength = ((ListView) CRUDtestRule.getActivity().findViewById(R.id.listView))
                .getAdapter().getCount();
        assertEquals(listLength,1);
    }

    /**
     * Retrieve test.
     */
    @Test
    public void BRetrieveTest(){
        Espresso.onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.name)).check(matches(withText(createname)));
        onView(withId(R.id.email)).check(matches(withText(createemail)));
        onView(withId(R.id.business)).check(matches(withText(createbusiness)));
        onView(withId(R.id.province)).check(matches(withText(createprovince)));
        onView(withId(R.id.address)).check(matches(withText(createaddress)));
        onView(withId(R.id.number)).check(matches(withText(createnumber)));
    }

    /**
     * Update test.
     */
    @Test
    public void CUpdateTest(){
        try{Thread.sleep(1000);}catch(Exception e) {}
        Espresso.onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.name)).perform(replaceText(updatename));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.updateButton)).perform(click());
        try{Thread.sleep(1000);}catch(Exception e) {}
        Espresso.onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.name)).check(matches(withText(updatename)));
    }

    /**
     * Delete test.
     */
    @Test
    public void DeleteTest(){
        try{Thread.sleep(1000);}catch(Exception e) {}
        Espresso.onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.deleteButton)).perform(click());
        int listLength = ((ListView) CRUDtestRule.getActivity().findViewById(R.id.listView)).getAdapter().getCount();
        assertEquals(listLength, 0);
    }
}


