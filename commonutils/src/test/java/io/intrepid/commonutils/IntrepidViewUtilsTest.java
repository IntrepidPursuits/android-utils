package io.intrepid.commonutils;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import io.intrepid.library.BuildConfig;

import static io.intrepid.commonutils.IntrepidViewUtils.getPositionInParent;
import static io.intrepid.commonutils.IntrepidViewUtils.setPaddingBottom;
import static io.intrepid.commonutils.IntrepidViewUtils.setPaddingLeft;
import static io.intrepid.commonutils.IntrepidViewUtils.setPaddingRight;
import static io.intrepid.commonutils.IntrepidViewUtils.setPaddingTop;
import static io.intrepid.commonutils.IntrepidViewUtils.toggleVisibility;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class IntrepidViewUtilsTest {

    @Test
    public void testToggleVisibilityGone() throws Exception {
        final View view = new View(RuntimeEnvironment.application);

        view.setVisibility(View.GONE);
        toggleVisibility(view);
        assertEquals(View.VISIBLE, view.getVisibility());
    }

    @Test
    public void testToggleVisibilityVisible() throws Exception {
        final View view = new View(RuntimeEnvironment.application);

        view.setVisibility(View.VISIBLE);
        toggleVisibility(view);
        assertEquals(View.GONE, view.getVisibility());
    }

    @Test
    public void testToggleVisibilityInvisible() throws Exception {
        final View view = new View(RuntimeEnvironment.application);

        view.setVisibility(View.INVISIBLE);
        toggleVisibility(view);
        assertEquals(View.VISIBLE, view.getVisibility());
    }

    @Test
    public void testSetPaddingLeft() throws Exception {
        final View view = new View(RuntimeEnvironment.application);

        view.setPadding(10, 10, 10, 10);
        setPaddingLeft(view, 20);

        assertEquals(20, view.getPaddingLeft());
        assertEquals(10, view.getPaddingRight());
        assertEquals(10, view.getPaddingTop());
        assertEquals(10, view.getPaddingBottom());
    }

    @Test
    public void testSetPaddingRight() throws Exception {
        final View view = new View(RuntimeEnvironment.application);

        view.setPadding(10, 10, 10, 10);
        setPaddingRight(view, 20);

        assertEquals(10, view.getPaddingLeft());
        assertEquals(20, view.getPaddingRight());
        assertEquals(10, view.getPaddingTop());
        assertEquals(10, view.getPaddingBottom());
    }

    @Test
    public void testSetPaddingTop() throws Exception {
        final View view = new View(RuntimeEnvironment.application);

        view.setPadding(10, 10, 10, 10);
        setPaddingTop(view, 20);

        assertEquals(10, view.getPaddingLeft());
        assertEquals(10, view.getPaddingRight());
        assertEquals(20, view.getPaddingTop());
        assertEquals(10, view.getPaddingBottom());
    }

    @Test
    public void testSetPaddingBottom() throws Exception {
        final View view = new View(RuntimeEnvironment.application);

        view.setPadding(10, 10, 10, 10);
        setPaddingBottom(view, 20);

        assertEquals(10, view.getPaddingLeft());
        assertEquals(10, view.getPaddingRight());
        assertEquals(10, view.getPaddingTop());
        assertEquals(20, view.getPaddingBottom());
    }

    @Test
    public void testGetPositionInParent() throws Exception {
        final View childView = Mockito.mock(View.class);

        assertEquals(-1, getPositionInParent(childView));

        final ViewGroup parentView = Mockito.mock(ViewGroup.class);
        when(childView.getParent()).thenReturn(parentView);
        when(parentView.indexOfChild(childView)).thenReturn(5);

        assertEquals(5, getPositionInParent(childView));
    }
}