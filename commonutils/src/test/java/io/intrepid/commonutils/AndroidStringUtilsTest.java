package io.intrepid.commonutils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@link AndroidStringUtils}
 */
@RunWith(RobolectricTestRunner.class)
public class AndroidStringUtilsTest {

    @Test
    public void testAppend() throws Exception {
        CharSequence expected = "String1String2";

        assertEquals(0, StringUtils.compare(expected, AndroidStringUtils.append("String1", "String2")));
    }

    @Test
    public void testAppendChar() throws Exception {
        CharSequence expected = "String1S";

        assertEquals(0, StringUtils.compare(expected, AndroidStringUtils.append("String1", 'S')));
    }
}

