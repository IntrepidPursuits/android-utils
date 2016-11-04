package io.intrepid.commonutils;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static io.intrepid.commonutils.ViewUtils.getPositionInParent;
import static io.intrepid.commonutils.ViewUtils.setHeight;
import static io.intrepid.commonutils.ViewUtils.setLayoutWeight;
import static io.intrepid.commonutils.ViewUtils.setMarginBottom;
import static io.intrepid.commonutils.ViewUtils.setMarginEnd;
import static io.intrepid.commonutils.ViewUtils.setMarginLeft;
import static io.intrepid.commonutils.ViewUtils.setMarginRight;
import static io.intrepid.commonutils.ViewUtils.setMarginStart;
import static io.intrepid.commonutils.ViewUtils.setMarginTop;
import static io.intrepid.commonutils.ViewUtils.setMargins;
import static io.intrepid.commonutils.ViewUtils.setPaddingBottom;
import static io.intrepid.commonutils.ViewUtils.setPaddingLeft;
import static io.intrepid.commonutils.ViewUtils.setPaddingRight;
import static io.intrepid.commonutils.ViewUtils.setPaddingTop;
import static io.intrepid.commonutils.ViewUtils.setVisibilities;
import static io.intrepid.commonutils.ViewUtils.setWidth;
import static io.intrepid.commonutils.ViewUtils.setWidthAndHeight;
import static io.intrepid.commonutils.ViewUtils.toggleVisibility;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class ViewUtilsTest {

    @Test
    public void testSetVisibilities() throws Exception {
        final View view1 = new View(RuntimeEnvironment.application);
        final View view2 = new View(RuntimeEnvironment.application);
        final View view3 = new View(RuntimeEnvironment.application);

        setVisibilities(View.VISIBLE, view1, view2, view3);
        assertViewsVisibility(View.VISIBLE, view1, view2, view3);

        setVisibilities(View.INVISIBLE, view1, view2, view3);
        assertViewsVisibility(View.INVISIBLE, view1, view2, view3);

        setVisibilities(View.GONE, view1, view2, view3);
        assertViewsVisibility(View.GONE, view1, view2, view3);
    }

    private static void assertViewsVisibility(int expectedVisibility, View... views) {
        for (View view : views) {
            assertEquals(expectedVisibility, view.getVisibility());

        }
    }

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
    public void testSetWidth() throws Exception {
        final ViewGroup container = new FrameLayout(RuntimeEnvironment.application);
        final View view = new View(RuntimeEnvironment.application);
        container.addView(view);
        setWidth(view, 50);
        assertEquals(50, view.getLayoutParams().width);
    }

    @Test
    public void testSetHeight() throws Exception {
        final ViewGroup container = new FrameLayout(RuntimeEnvironment.application);
        final View view = new View(RuntimeEnvironment.application);
        container.addView(view);
        setHeight(view, 50);
        assertEquals(50, view.getLayoutParams().height);
    }

    @Test
    public void testSetWidthAndHeight() throws Exception {
        final ViewGroup container = new FrameLayout(RuntimeEnvironment.application);
        final View view = new View(RuntimeEnvironment.application);
        container.addView(view);
        setWidthAndHeight(view, 50, 70);
        assertEquals(50, view.getLayoutParams().width);
        assertEquals(70, view.getLayoutParams().height);
    }

    @Test
    public void testSetWeight() throws Exception {
        final LinearLayout container = new LinearLayout(RuntimeEnvironment.application);
        final View view = new View(RuntimeEnvironment.application);
        container.addView(view);
        setLayoutWeight(view, 5);
        assertEquals(5, ((LinearLayout.LayoutParams) view.getLayoutParams()).weight, 0.0000001);
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Test
    public void testSetMarginStart() throws Exception {
        final ViewGroup container = new FrameLayout(RuntimeEnvironment.application);
        final View view = new View(RuntimeEnvironment.application);
        container.addView(view);
        setMarginStart(view, 50);
        assertEquals(50, ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).getMarginStart());
        assertEquals(50, ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).leftMargin);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Test
    public void testSetMarginEnd() throws Exception {
        final ViewGroup container = new FrameLayout(RuntimeEnvironment.application);
        final View view = new View(RuntimeEnvironment.application);
        container.addView(view);
        setMarginEnd(view, 50);
        assertEquals(50, ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).getMarginEnd());
        assertEquals(50, ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).rightMargin);
    }

    @Test
    public void testSetMarginLeft() throws Exception {
        final ViewGroup container = new FrameLayout(RuntimeEnvironment.application);
        final View view = new View(RuntimeEnvironment.application);
        container.addView(view);
        setMarginLeft(view, 50);
        assertEquals(50, ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).leftMargin);
    }

    @Test
    public void testSetMarginRight() throws Exception {
        final ViewGroup container = new FrameLayout(RuntimeEnvironment.application);
        final View view = new View(RuntimeEnvironment.application);
        container.addView(view);
        setMarginRight(view, 50);
        assertEquals(50, ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).rightMargin);
    }

    @Test
    public void testSetMarginTop() throws Exception {
        final ViewGroup container = new FrameLayout(RuntimeEnvironment.application);
        final View view = new View(RuntimeEnvironment.application);
        container.addView(view);
        setMarginTop(view, 50);
        assertEquals(50, ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin);
    }

    @Test
    public void testSetMarginBottom() throws Exception {
        final ViewGroup container = new FrameLayout(RuntimeEnvironment.application);
        final View view = new View(RuntimeEnvironment.application);
        container.addView(view);
        setMarginBottom(view, 50);
        assertEquals(50, ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).bottomMargin);
    }

    @Test
    public void testSetMarginAll() throws Exception {
        final ViewGroup container = new FrameLayout(RuntimeEnvironment.application);
        final View view = new View(RuntimeEnvironment.application);
        container.addView(view);
        setMargins(view, 50);
        assertEquals(50, ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).leftMargin);
        assertEquals(50, ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin);
        assertEquals(50, ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).rightMargin);
        assertEquals(50, ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).bottomMargin);
    }

    @Test
    public void testSetMarginIndividually() throws Exception {
        final ViewGroup container = new FrameLayout(RuntimeEnvironment.application);
        final View view = new View(RuntimeEnvironment.application);
        container.addView(view);
        setMargins(view, 10, 20, 30, 40);
        assertEquals(10, ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).leftMargin);
        assertEquals(20, ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin);
        assertEquals(30, ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).rightMargin);
        assertEquals(40, ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).bottomMargin);
    }
}
