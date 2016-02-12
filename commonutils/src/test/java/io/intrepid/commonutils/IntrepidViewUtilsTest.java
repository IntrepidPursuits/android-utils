package io.intrepid.commonutils;

import android.view.View;
import android.view.ViewGroup;

import org.junit.Test;
import org.mockito.Mockito;

import static io.intrepid.commonutils.IntrepidViewUtils.getPositionInParent;
import static io.intrepid.commonutils.IntrepidViewUtils.setPaddingBottom;
import static io.intrepid.commonutils.IntrepidViewUtils.setPaddingRight;
import static io.intrepid.commonutils.IntrepidViewUtils.setPaddingTop;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IntrepidViewUtilsTest {

    private int left = 10;
    private int right = 20;
    private int top = 30;
    private int bottom = 40;

    @Test
    public void testSetPaddingRight() throws Exception {
        final View view = Mockito.mock(View.class);

        when(view.getPaddingLeft()).thenReturn(left);
        when(view.getPaddingTop()).thenReturn(top);
        when(view.getPaddingBottom()).thenReturn(bottom);

        setPaddingRight(view, right);

        verify(view).setPadding(eq(left), eq(top), eq(right), eq(bottom));
    }

    @Test
    public void testSetPaddingTop() throws Exception {
        final View view = Mockito.mock(View.class);

        when(view.getPaddingLeft()).thenReturn(left);
        when(view.getPaddingRight()).thenReturn(right);
        when(view.getPaddingBottom()).thenReturn(bottom);

        setPaddingTop(view, top);

        verify(view).setPadding(eq(left), eq(top), eq(right), eq(bottom));
    }

    @Test
    public void testSetPaddingBottom() throws Exception {
        final View view = Mockito.mock(View.class);

        when(view.getPaddingLeft()).thenReturn(left);
        when(view.getPaddingRight()).thenReturn(right);
        when(view.getPaddingTop()).thenReturn(top);

        setPaddingBottom(view, bottom);

        verify(view).setPadding(eq(left), eq(top), eq(right), eq(bottom));
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