package io.intrepid.commonutils;

import android.net.Uri;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
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

    @Test
    public void testValidUri() throws URISyntaxException {
        final String VALID_URI = "http://www.example.com";
        Uri uri = AndroidStringUtils.parseHttpUriFromString(VALID_URI);

        assertThat(uri.toString(), is(VALID_URI));
    }

    @Test
    public void testValidHttpUriMissingProtocol() throws URISyntaxException {
        final String VALID_URI_MISSING_PROTOCOL = "www.example.com";
        Uri uri = AndroidStringUtils.parseHttpUriFromString(VALID_URI_MISSING_PROTOCOL);

        assertThat(uri.toString(), is("http://" + VALID_URI_MISSING_PROTOCOL));
    }

    @Test
    public void testValidHttpsUriMissingProtocol() throws URISyntaxException {
        final String VALID_URI_MISSING_PROTOCOL = "www.example.com";
        Uri uri = AndroidStringUtils.parseHttpsUriFromString(VALID_URI_MISSING_PROTOCOL);

        assertThat(uri.toString(), is("https://" + VALID_URI_MISSING_PROTOCOL));
    }

    @Test(expected = URISyntaxException.class)
    public void testInvalidUri() throws URISyntaxException {
        AndroidStringUtils.parseHttpUriFromString("<invalid uri>");
    }
}
