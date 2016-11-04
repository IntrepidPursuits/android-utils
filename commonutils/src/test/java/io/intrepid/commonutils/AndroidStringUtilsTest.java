package io.intrepid.commonutils;

import android.text.SpannableStringBuilder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import io.intrepid.commonutils.util.MockedStringSpannableStringBuilder;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@link AndroidStringUtils}
 */
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({ "org.mockito.*", "android.*" })
@PrepareForTest({ SpannableStringBuilder.class, AndroidStringUtils.class })
public class AndroidStringUtilsTest {

    @Before
    public void setup() {
        MockedStringSpannableStringBuilder.setup();
    }

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

