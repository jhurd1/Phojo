package com.example.phojo;
/*import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import androidx.test.core.app.ApplicationProvider;
import static com.google.common.truth.Truth.assertThat;*/


//@RunWith(MockitoJUnitRunner.class);

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PhojoUnitTest {
    // tested the following method for syntactical correctness
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void division_isCorrect() {
        assertEquals(12, 24 / 2);
    }


    /*private static final String FAKE_STRING = "HELLO_WORLD";
    private Context context = ApplicationProvider.getApplicationContext();

    @Test
    public void readStringFromContext_LocalizedString() {
        // Given a Context object retrieved from Robolectric...
        ClassUnderTest myObjectUnderTest = new ClassUnderTest(context);

        // ...when the string is returned from the object under test...
        String result = myObjectUnderTest.getHelloWorldString();

        // ...then the result should be the expected one.
        assertThat(result).isEqualTo(FAKE_STRING);
    }

    //https://developer.android.com/training/testing/unit-testing/local-unit-tests.html#include
    // -framework-dependencies

    /*@Mock
    Context mockContext;

    @Test
    public void readStringFromContext_LocalizedString() {
        // Given a mocked Context injected into the object under test...
        when(mockContext.getString(R.string.hello_world))
                .thenReturn(FAKE_STRING);
        ClassUnderTest myObjectUnderTest = new ClassUnderTest(mockContext);

        // ...when the string is returned from the object under test...
        String result = myObjectUnderTest.getHelloWorldString();

        // ...then the result should be the expected one.
        assertThat(result, is(FAKE_STRING));
    }*/

}
