package io.intrepid.commonutils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.intrepid.commonutils.JavaUtils.byteToUnsignedInt;
import static io.intrepid.commonutils.JavaUtils.bytesToHexString;
import static io.intrepid.commonutils.JavaUtils.moveElementInList;
import static io.intrepid.commonutils.JavaUtils.nonNullString;
import static org.junit.Assert.assertEquals;

public class JavaUtilsTest {

    @Test
    public void testBytesToHexString() throws Exception {
        assertEquals("", bytesToHexString(new byte[0]));
        assertEquals("ab12", bytesToHexString(new byte[] { (byte) 0xab, (byte) 0x12 }));
        assertEquals("0a", bytesToHexString(new byte[] { 0x0a }));
    }

    @Test
    public void testByteToUnsignedInt() throws Exception {
        assertEquals(5, byteToUnsignedInt((byte) 0x05));
        assertEquals(32, byteToUnsignedInt((byte) 0x20));
        assertEquals(255, byteToUnsignedInt((byte) 0xff));
        assertEquals(0, byteToUnsignedInt((byte) 0x0));
    }

    @Test
    public void testNonNullString() throws Exception {
        assertEquals("test", nonNullString("test"));
        assertEquals("", nonNullString(null));
    }

    @Test
    public void testMoveElementInList() throws Exception {
        List<Integer> originalList = Arrays.asList(0, 1, 2, 3, 4, 5);

        List<Integer> list1 = new ArrayList<>(originalList);
        moveElementInList(list1, 1, 3);
        assertEquals(Arrays.asList(0, 2, 3, 1, 4, 5), list1);

        List<Integer> list2 = new ArrayList<>(originalList);
        moveElementInList(list2, 3, 1);
        assertEquals(Arrays.asList(0, 3, 1, 2, 4, 5), list2);

        List<Integer> list3 = new ArrayList<>(originalList);
        moveElementInList(list3, 2, 2);
        assertEquals(Arrays.asList(0, 1, 2, 3, 4, 5), list3);
    }
}
