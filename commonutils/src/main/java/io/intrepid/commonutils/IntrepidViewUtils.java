package io.intrepid.commonutils;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Toast;

public class IntrepidViewUtils {
    /**
     * Toggle a view's visibility between VISIBLE and GONE. If input view's visibility is INVISIBLE,
     * the method will toggle it to VISIBLE
     *
     * @param view the view that will have its visibility toggled
     */
    public static void toggleVisibility(@NonNull View view) {
        final int currentVisibility = view.getVisibility();
        view.setVisibility(currentVisibility == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    /**
     * Sets a view's left padding while retaining the padding from other sides
     *
     * @param view       the input view
     * @param newPadding the new left padding in pixels
     */
    public static void setPaddingLeft(@NonNull View view, int newPadding) {
        view.setPadding(newPadding, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
    }

    /**
     * Sets a view's right padding while retaining the padding from other sides
     *
     * @param view       the input view
     * @param newPadding the new right padding in pixels
     */
    public static void setPaddingRight(@NonNull View view, int newPadding) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), newPadding, view.getPaddingBottom());
    }

    /**
     * Sets a view's top padding while retaining the padding from other sides
     *
     * @param view       the input view
     * @param newPadding the new top padding in pixels
     */
    public static void setPaddingTop(@NonNull View view, int newPadding) {
        view.setPadding(view.getPaddingLeft(), newPadding, view.getPaddingRight(), view.getPaddingBottom());
    }

    /**
     * Sets a view's bottom padding while retaining the padding from other sides
     *
     * @param view       the input view
     * @param newPadding the new bottom padding in pixels
     */
    public static void setPaddingBottom(@NonNull View view, int newPadding) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), newPadding);
    }

    /**
     * Find the index/position of a view within its parent
     *
     * @param view the input view
     * @return the index/position of the input view within its parent, or -1 if it doesn't have a parent
     */
    public static int getPositionInParent(@NonNull View view) {
        ViewParent parentView = view.getParent();
        if (parentView != null && parentView instanceof ViewGroup) {
            return ((ViewGroup) parentView).indexOfChild(view);
        } else {
            return -1;
        }
    }

    /**
     * Convenience method for setting the ripple hotspot during an onTouch event for Lollipop+ devices
     *
     * @param view  The input view
     * @param event The MotionEvent object from onTouch method
     */
    public static void setTouchEventHotSpot(View view, MotionEvent event) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Drawable background = view.getBackground();
            if (background != null) {
                background.setHotspot(event.getX(), event.getY());
            }
        }
    }

    /**
     * Shows a Toast next to a view/button describing what it does
     * <p/>
     * This is basically copied over from ActionMenuItemView.onLongClick()
     *
     * @param anchorView The view where the toast will appear next to
     * @param text       the text inside the Toast
     */
    public static void showToolTip(@NonNull View anchorView, @NonNull CharSequence text) {
        final int[] screenPos = new int[2];
        final Rect displayFrame = new Rect();
        anchorView.getLocationOnScreen(screenPos);
        anchorView.getWindowVisibleDisplayFrame(displayFrame);

        final Context context = anchorView.getContext();
        final int width = anchorView.getWidth();
        final int height = anchorView.getHeight();
        final int midy = screenPos[1] + height / 2;
        int referenceX = screenPos[0] + width / 2;
        if (ViewCompat.getLayoutDirection(anchorView) == ViewCompat.LAYOUT_DIRECTION_LTR) {
            final int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
            referenceX = screenWidth - referenceX; // mirror
        }
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        if (midy < displayFrame.height()) {
            // Show along the top; follow action buttons
            toast.setGravity(Gravity.TOP | GravityCompat.END, referenceX,
                             screenPos[1] + height - displayFrame.top);
        } else {
            // Show along the bottom center
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, height);
        }
        toast.show();
    }

    /**
     * Similar to {@link #showToolTip(View, CharSequence)}, but takes in a string resource instead
     */
    public static void showToolTip(@NonNull View anchorView, int textResource) {
        showToolTip(anchorView, anchorView.getResources().getString(textResource));
    }

    /**
     * Similar to {@link #showToolTip(View, CharSequence)}, but displays the view's contentDescription
     */
    public static void showToolTip(@NonNull View view) {
        showToolTip(view, view.getContentDescription());
    }

    /**
     * In older version of android (<5.0), setting certain background drawable (mainly layer-lists) will cause view's
     * padding to reset. So we need to explicitly remember and restore those paddings
     * <p/>
     * https://code.google.com/p/android/issues/detail?id=27235
     *
     * @param view          The view whose background will be set
     * @param backgroundRes The drawable resource id for the background
     */
    public static void setBackgroundWhileRetainingPadding(@NonNull View view, @DrawableRes int backgroundRes) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            int paddingTop = view.getPaddingTop();
            int paddingLeft = view.getPaddingLeft();
            int paddingRight = view.getPaddingRight();
            int paddingBottom = view.getPaddingBottom();
            view.setBackgroundResource(backgroundRes);
            view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        } else {
            view.setBackgroundResource(backgroundRes);
        }
    }
}
