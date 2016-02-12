package io.intrepid.commonutils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import org.junit.Test;
import org.mockito.Mockito;

import static io.intrepid.commonutils.IntrepidAndroidUtils.convertDpToPixel;
import static io.intrepid.commonutils.IntrepidAndroidUtils.convertPixelsToDp;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class IntrepidAndroidUtilsTest {

    @Test
    public void testConvertDpToPixel() throws Exception {
        Context context = Mockito.mock(Context.class);
        Resources resources = Mockito.mock(Resources.class);
        DisplayMetrics displayMetrics = Mockito.mock(DisplayMetrics.class);
        when(context.getResources()).thenReturn(resources);
        when(resources.getDisplayMetrics()).thenReturn(displayMetrics);

        displayMetrics.densityDpi = 320;

        assertEquals(10, convertDpToPixel(context, 5), 0);
    }

    @Test
    public void testConvertPixelsToDp() throws Exception {
        Context context = Mockito.mock(Context.class);
        Resources resources = Mockito.mock(Resources.class);
        DisplayMetrics displayMetrics = Mockito.mock(DisplayMetrics.class);
        when(context.getResources()).thenReturn(resources);
        when(resources.getDisplayMetrics()).thenReturn(displayMetrics);

        displayMetrics.densityDpi = 320;

        assertEquals(5, convertPixelsToDp(context, 10), 0);
    }
}