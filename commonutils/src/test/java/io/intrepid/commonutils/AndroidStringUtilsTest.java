package io.intrepid.commonutils;

import android.net.Uri;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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

    @Test
    public void testValidUrl() {
        final String VALID_URL = "http://www.example.com";
        Uri uri = AndroidStringUtils.parseUriFromString(VALID_URL);

        assertNotNull(uri);
        assertThat(uri.toString(), is(VALID_URL));
    }

    @Test
    public void testValidUrlMissingProtocol() {
        final String VALID_URL_MISSING_PROTOCOL = "www.example.com";
        Uri uri = AndroidStringUtils.parseUriFromString(VALID_URL_MISSING_PROTOCOL);

        assertNotNull(uri);
        assertThat(uri.toString(), is("http://" + VALID_URL_MISSING_PROTOCOL));
    }

    @Test
    public void testInvalidUrl() {
        final String INVALID_URL = "<not a url>";
        Uri uri = AndroidStringUtils.parseUriFromString(INVALID_URL);

        assertNull(uri);
    }
}

