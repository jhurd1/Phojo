/*
package com.Phojo.unitTests;

import android.content.Context;
import android.view.View;
import androidx.test.core.app.ApplicationProvider;
//import com.example.phojo.MainActivity;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

*/
/***********************************
 * MAINACTIVITYTEST Class
 **********************************//*

public class MainActivityTest
{
    */
/***********************************
     * PRIVATE MEMBERS
     **********************************//*

    private View mActivity = null;
    private static final String FAKE_STRING = "test string";
    public boolean match = false;
    private Context context = ApplicationProvider.getApplicationContext();

    */
/***********************************
     * Simple math test
     **********************************//*

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    */
/***********************************
     * Test reading a string from
     * context
     **********************************//*

    */
/*@Test
    public void readStringFromContext_LocalizedString()
    {
        MainActivity maObject = new MainActivity();
        String result = maObject.getTestString();
        assertThat(result).equals(FAKE_STRING);
    }*//*


    private Object assertThat(String result) {
        if(result == FAKE_STRING)
        {
            match = true;
        }
        return match;
    }

    */
/***********************************
     * Test successful launch of main
     * activity.
     * Adapted from:
     * https://www.youtube.com/watch?v=
     * ..._TR6QcRozAg&ab_channel=LearnShareAnythingAnyone
     **********************************//*


    */
/*public void setUp() throws Exception
    {
        // to set up the test, get the activity
        MainActivity mActivityTestRule = new MainActivity();
        mActivity = mActivityTestRule.getActivity();
    }*//*


    @Test
    public void testLaunch()
    {
        //Since we're testing a test for the id of the ViewText in MainActivity
        View view = mActivity;
        //assertNotNull(view); // null value from user in view? test fails.
    }

    public void tearDown() throws Exception
    {
        // to tear down, assign mActivity to null
        mActivity = null;
    }
}*/
