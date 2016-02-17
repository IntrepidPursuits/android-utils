package io.intrepid.commonutils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import io.intrepid.library.BuildConfig;

import static io.intrepid.commonutils.IntrepidAndroidUtils.convertDpToPixel;
import static io.intrepid.commonutils.IntrepidAndroidUtils.convertPixelsToDp;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class IntrepidAndroidUtilsTest {

    @Test
    public void testConvertDpToPixel() throws Exception {
        Context context = Mockito.mock(Context.class);
        Resources resources = Mockito.mock(Resources.class);
        DisplayMetrics displayMetrics = Mockito.mock(DisplayMetrics.class);
        when(context.getResources()).thenReturn(resources);
        when(resources.getDisplayMetrics()).thenReturn(displayMetrics);

        displayMetrics.density = 2;

        assertEquals(10, convertDpToPixel(context, 5), 0);
    }

    @Test
    public void testConvertPixelsToDp() throws Exception {
        Context context = Mockito.mock(Context.class);
        Resources resources = Mockito.mock(Resources.class);
        DisplayMetrics displayMetrics = Mockito.mock(DisplayMetrics.class);
        when(context.getResources()).thenReturn(resources);
        when(resources.getDisplayMetrics()).thenReturn(displayMetrics);

        displayMetrics.density = 2;

        assertEquals(5, convertPixelsToDp(context, 10), 0);
    }
}