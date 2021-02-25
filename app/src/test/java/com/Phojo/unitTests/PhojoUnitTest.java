package com.Phojo.unitTests;

import org.mockito.Mock;
import androidx.test.core.app.ApplicationProvider;
import static com.google.common.truth.Truth.assertThat;
import android.content.Context;
import com.example.phojo.MainActivity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.Assert;

/****************************************
 * PUBLIC CLASS PHOJOUNITTEST
 * Run unit tests on Phojo
 ***************************************/
@RunWith(MockitoJUnitRunner.class)
public class PhojoUnitTest
{

    /****************************************
     * PRIVATE MEMBERS
     ***************************************/
    private static final int FAKE_STRING = 12;
    private Context context = ApplicationProvider.getApplicationContext();
    //private static UnicodeSet set = FAKE_STRING;
    //private static final UnicodeSetIterator R = new UnicodeSetIterator(set);

    public static int getFakeString() {
        return FAKE_STRING;
    }

    /****************************************
     * TEST: Addition_isCorrect
     * Tests for addition correctness success.
     ***************************************/
    @Test
    public void addition_isCorrect()
    {
        assertEquals(4, 2 + 2);
    }

    /*@Test
    public void readStringFromContext_LocalizedString() {
        // Given a Context object retrieved from Robolectric...
        MainActivity myObjectUnderTest = new MainActivity(context);

        // ...when the string is returned from the object under test...
        String result = myObjectUnderTest.getHelloWorldString();

        // ...then the result should be the expected one.
        //assertThat(result).isEqualTo(FAKE_STRING);
    }*/

    //https://developer.android.com/training/testing/unit-testing/local-unit-tests.html#include
    // -framework-dependencies

    /****************************************
     * TEST: readFromContext
     * Tests for correct string reading.
     ***************************************/
    @Mock
    Context mockContext;

    @Test
    public void readStringFromContext_LocalizedString()
    {
        // Given a mocked Context injected into the object under test...
        when(mockContext.getString(getFakeString()))
                .thenReturn(String.valueOf(FAKE_STRING));
        MainActivity myObjectUnderTest = new MainActivity(mockContext);

        // ...when the string is returned from the object under test...
        String result = myObjectUnderTest.getHelloWorldString();

        // ...then the result should be the expected one.
        assertEquals(result, (FAKE_STRING));
    }

}
