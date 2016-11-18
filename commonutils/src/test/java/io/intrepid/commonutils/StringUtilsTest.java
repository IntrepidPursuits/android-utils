package io.intrepid.commonutils;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests {@link StringUtils}
 */
public class StringUtilsTest {

    @Test
    public void testIsEmpty() {
        assertFalse(StringUtils.isEmpty("test"));
        assertTrue(StringUtils.isEmpty(null));
        assertTrue(StringUtils.isEmpty(""));
    }

    @Test
    public void testEquals() {
        assertTrue(StringUtils.equals("String1", "String1"));
    }

    @Test
    public void testNotEquals() {
        assertFalse(StringUtils.equals("String1000", "String1"));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(StringUtils.equals("String", null));
        assertFalse(StringUtils.equals(null, "String"));
        assertTrue(StringUtils.equals(null, null));
    }

    @Test
    public void testJoin() {
        List<String> strings = Arrays.asList("a", "bb", "ccc", "d");
        assertEquals("a|bb|ccc|d", StringUtils.join("|", strings));
    }

    @Test
    public void testStripSurroundingWhiteSpace() {
        CharSequence expected = "abcDe f";

        assertEquals(expected, StringUtils.stripSurroundingWhiteSpace("   abcDe f \n\t "));
        assertEquals(expected, StringUtils.stripSurroundingWhiteSpace("abcDe f"));
        assertEquals(expected, StringUtils.stripSurroundingWhiteSpace("abcDe f  \t  \n "));
        assertEquals(expected, StringUtils.stripSurroundingWhiteSpace("\t abcDe f"));
    }

    @Test
    public void testStripSurroundingWhiteSpaceEmpty() {
        assertEquals("", StringUtils.stripSurroundingWhiteSpace(" \t  \n\n\n "));
        assertNull(StringUtils.stripSurroundingWhiteSpace(null));
    }

    @Test
    public void testReplaceNonBoringCharacters() {
        CharSequence expected = "[]Anton[][]\r Spaans[]";
        assertEquals(expected, StringUtils.replaceNonBoringCharacters("\u0590Anton\n\t\r Spaans\u1234", "[]"));
    }

    @Test
    public void testReplaceNonBoringCharactersEmptyInput() {
        CharSequence expected = "";
        assertEquals(expected, StringUtils.replaceNonBoringCharacters("", "[]"));
    }

    @Test
    public void testStripNonAlphaNumeric() {
        CharSequence expected = "Rem0veAllNonAlphaNumCharactersf";
        assertEquals(expected, StringUtils.stripNonAlphaNumeric("Rem0ve\nAll Non Alpha-Num Characters!\u0066"));
    }

    @Test
    public void testIsEmailAddress() {
        assertTrue(StringUtils.isEmailAddress("Tim.Horton@q.com"));
    }

    @Test
    public void testIsNotEmailAddress1() {
        assertFalse(StringUtils.isEmailAddress("Tim.Horton_q.com"));
    }

    @Test
    public void testIsNotEmailAddress2() {
        assertFalse(StringUtils.isEmailAddress("@q.com"));
    }

    @Test
    public void testIsNotEmailAddress3() {
        assertFalse(StringUtils.isEmailAddress("Tim.Horton@"));
    }

    @Test
    public void testIsNotEmailAddress4() {
        assertFalse(StringUtils.isEmailAddress("Tim.Horton@nl"));
    }

    @Test
    public void testIsNotEmailAddress5() {
        assertFalse(StringUtils.isEmailAddress("Tim.Horton@nl.c"));
    }

    private enum TestEnum {
        TEST_ENUM1, TEST_ENUM2
    }

    @Test
    public void testEnumToString() {
        assertEquals(TestEnum.TEST_ENUM1.name(), StringUtils.toEnumString(TestEnum.TEST_ENUM1));
        //noinspection unchecked
        assertNull(StringUtils.toEnumString(null));
    }

    @Test
    public void testStringToEnum() {
        assertEquals(TestEnum.TEST_ENUM2, StringUtils.toEnum(TestEnum.class, TestEnum.TEST_ENUM2.name()));
        assertNull(StringUtils.toEnum(TestEnum.class, null));
    }

    @Test
    public void testStringToEnumFail() {
        try {
            assertEquals(TestEnum.TEST_ENUM2, StringUtils.toEnum(TestEnum.class, "HELLO"));
        } catch (IllegalArgumentException iae) {
            // OK
            return;
        }

        fail("Expected an IllegalArgumentException");
    }

    @Test
    public void testParseSeparatedArray() {
        String[] expected = new String[] {
                "Element1", "Element2", "Element 3", "Element 4"
        };

        assertArrayEquals(expected, StringUtils.parseSeparatedArray("  \n Element1 |Element2 | Element 3|Element 4   \n", "|"));
    }


    @Test
    public void testParseSeparatedList() {
        String[] expcected = new String[] {
                "Element1", "Element2", "Element 3", "Element 4"
        };

        assertEquals(Arrays.asList(expcected), StringUtils.parseSeparatedList("  \n Element1 |Element2 | Element 3|Element 4   \n || | |", "|"));
    }

    @Test
    public void testSeparateItemsWith() {
        String expected = "Hello, This, is, my name";
        assertEquals(0, StringUtils.compare(expected, StringUtils.separateItemsWith(", ", "Hello", "This", null, "is", "", "my name")));
    }


    @Test
    public void separateItemsAsStringsWith() {
        String expected = "10, Hello, This, is, my name";
        assertEquals(expected, StringUtils.separateItemsAsStringsWith(", ", Arrays.asList(10, "Hello", "This", null, "is", "", "my name"))
                .toString());
    }

    @Test
    public void testRepeat1() {
        String expected = "--";
        assertEquals(expected, StringUtils.repeat(null, "-", 3));
    }

    @Test
    public void testRepeat2() {
        String expected = "Test-Test-Test";
        assertEquals(expected, StringUtils.repeat("Test", "-", 3));
    }

    @Test
    public void testRepeat3() {
        String expected = "TestTestTest";
        assertEquals(expected, StringUtils.repeat("Test", null, 3));
    }

    @Test
    public void testRepeat4() {
        assertEquals(null, StringUtils.repeat(null, null, 3));
    }

    @Test
    public void testRepeat5() {
        assertEquals("", StringUtils.repeat("a", "b", 0));
    }

    @Test
    public void testRepeat6() {
        String expected = "Test";
        assertEquals(expected, StringUtils.repeat(expected, "-", 1));
    }

    @Test
    public void testCompare1() {
        assertEquals(0, StringUtils.compare("String1", "String1"));
    }

    @Test
    public void testCompare2() {
        assertEquals("String1".compareTo("String1000"), StringUtils.compare("String1", "String1000"));
    }

    @Test
    public void testCompare3() {
        assertEquals("String1000".compareTo("String1"), StringUtils.compare("String1000", "String1"));
    }

    @Test
    public void testCompare4() {
        assertEquals("String1".compareTo("StrIng1"), StringUtils.compare("String1", "StrIng1"));
    }

    @Test
    public void testCompare5() {
        assertEquals(0, StringUtils.compare(null, null));
    }

    @Test
    public void testCompare6() {
        assertEquals(1, StringUtils.compare("", null));
    }

    @Test
    public void testCompare7() {
        assertEquals(-1, StringUtils.compare(null, ""));
    }

    @Test
    public void testIndexOf1() {
        assertEquals("String1".indexOf('g', 2), StringUtils.indexOf("String1", 'g', 2));
    }

    @Test
    public void testIndexOf2() {
        assertEquals("String1".indexOf('q', 2), StringUtils.indexOf("String1", 'q', 2));
    }

    @Test
    public void testIS2StringSmall() throws Exception {
        final String expected = "This is the expected string.";

        InputStream is = new InputStream() {
            int pos = -1;

            @Override
            public int read() throws IOException {
                final int nextPos = ++pos;
                return nextPos < expected.length() ? expected.charAt(nextPos) : -1;
            }
        };

        assertEquals(expected, StringUtils.toString(is));
    }

    @Test
    public void testIS2StringLarge() throws Exception {
        String chunk = "This is the expected string.\n";
        for (int i = 0; i < 10; i++) {
            chunk += chunk;
        }

        final String expected = chunk;

        InputStream is = new InputStream() {
            int pos = -1;
            @Override
            public int read() throws IOException {
                final int nextPos = ++pos;
                return nextPos < expected.length() ? expected.charAt(nextPos) : -1;
            }
        };

        assertEquals(expected, StringUtils.toString(is));
    }

    @Test
    public void testLevenshteinEmptyString() throws Exception {
        assertEquals(0, StringUtils.getLevenshteinDistance("", ""));
        assertEquals(1, StringUtils.getLevenshteinDistance("a", ""));
        assertEquals(1, StringUtils.getLevenshteinDistance("", "a"));
        assertEquals(3, StringUtils.getLevenshteinDistance("abc", ""));
        assertEquals(3, StringUtils.getLevenshteinDistance("", "abc"));
    }

    @Test
    public void testLevenshteinEqualStrings() throws Exception {
        assertEquals(0, StringUtils.getLevenshteinDistance("", ""));
        assertEquals(0, StringUtils.getLevenshteinDistance("a", "a"));
        assertEquals(0, StringUtils.getLevenshteinDistance("abc", "abc"));
    }

    @Test
    public void testLevenshteinInsertions() throws Exception {
        assertEquals(1, StringUtils.getLevenshteinDistance("", "a"));
        assertEquals(1, StringUtils.getLevenshteinDistance("a", "ab"));
        assertEquals(1, StringUtils.getLevenshteinDistance("b", "ab"));
        assertEquals(1, StringUtils.getLevenshteinDistance("ac", "abc"));
        assertEquals(6, StringUtils.getLevenshteinDistance("abcdefg", "xabxcdxxefxgx"));
    }

    @Test
    public void testLevenshteinDeletions() throws Exception {
        assertEquals(1, StringUtils.getLevenshteinDistance("a", ""));
        assertEquals(1, StringUtils.getLevenshteinDistance("ab", "a"));
        assertEquals(1, StringUtils.getLevenshteinDistance("ab", "b"));
        assertEquals(1, StringUtils.getLevenshteinDistance("abc", "ac"));
        assertEquals(6, StringUtils.getLevenshteinDistance("xabxcdxxefxgx", "abcdefg"));
    }

    @Test
    public void testLevenshteinSubstitutions() throws Exception {
        assertEquals(1, StringUtils.getLevenshteinDistance("a", "b"));
        assertEquals(1, StringUtils.getLevenshteinDistance("ab", "ac"));
        assertEquals(1, StringUtils.getLevenshteinDistance("ac", "bc"));
        assertEquals(1, StringUtils.getLevenshteinDistance("abc", "axc"));
        assertEquals(6, StringUtils.getLevenshteinDistance("xabxcdxxefxgx", "1ab2cd34ef5g6"));
    }

    @Test
    public void testLevenshteinMixed() throws Exception {
        assertEquals(3, StringUtils.getLevenshteinDistance("example", "samples"));
        assertEquals(6, StringUtils.getLevenshteinDistance("sturgeon", "urgently"));
        assertEquals(6, StringUtils.getLevenshteinDistance("levenshtein", "frankenstein"));
        assertEquals(5, StringUtils.getLevenshteinDistance("distance", "difference"));
        assertEquals(7, StringUtils.getLevenshteinDistance("java was neat", "scala is great"));
        assertEquals(31, StringUtils.getLevenshteinDistance("Wilhelmus van Nassouwe ben ick van Duytschen bloet", "William of Nassau am I of German1blood"));
    }
}
