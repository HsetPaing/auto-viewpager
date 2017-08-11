package com.santalu.autoviewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by santalu on 09/08/2017.
 */

public class AutoViewPager extends ViewPager {

    private static final String TAG = AutoViewPager.class.getSimpleName();

    private static final int DEFAULT_DURATION = 10000;

    private int mDuration = DEFAULT_DURATION;
    private float mStartX;
    private boolean mAutoScrollEnabled;
    private boolean mIndeterminate;

    private final Runnable mAutoScroll = new Runnable() {
        @Override public void run() {
            if (!isShown()) {
                return;
            }
            if (getCurrentItem() == getAdapter().getCount() - 1) {
                setCurrentItem(0);
            } else {
                setCurrentItem(getCurrentItem() + 1);
            }
            postDelayed(mAutoScroll, mDuration);
        }
    };

    public AutoViewPager(Context context) {
        super(context);
    }

    public AutoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.AutoViewPager);
        try {
            setAutoScrollEnabled(a.getBoolean(R.styleable.AutoViewPager_avp_autoScroll, false));
            setIndeterminate(a.getBoolean(R.styleable.AutoViewPager_avp_indeterminate, false));
            setDuration(a.getInteger(R.styleable.AutoViewPager_avp_duration, DEFAULT_DURATION));
        } finally {
            a.recycle();
        }
    }

    public void setIndeterminate(boolean indeterminate) {
        mIndeterminate = indeterminate;
    }

    public void setAutoScrollEnabled(boolean enabled) {
        if (mAutoScrollEnabled == enabled) {
            return;
        }
        mAutoScrollEnabled = enabled;
        stopAutoScroll();
        if (enabled) {
            startAutoScroll();
        }
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    private void startAutoScroll() {
        postDelayed(mAutoScroll, mDuration);
    }

    private void stopAutoScroll() {
        removeCallbacks(mAutoScroll);
    }

    @Override public boolean onInterceptTouchEvent(MotionEvent event) {
        try {
            int action = event.getActionMasked();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    mStartX = event.getX();
                    break;
            }
            return super.onInterceptTouchEvent(event);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        try {
            if (mIndeterminate) {
                if (getCurrentItem() == 0 || getCurrentItem() == getAdapter().getCount() - 1) {
                    final int action = event.getAction();
                    float x = event.getX();
                    switch (action & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_UP:
                            if (getCurrentItem() == getAdapter().getCount() - 1 && x < mStartX) {
                                post(new Runnable() {
                                    @Override public void run() {
                                        setCurrentItem(0);
                                    }
                                });
                            }
                            break;
                    }
                } else {
                    mStartX = 0;
                }
            }
            return super.onTouchEvent(event);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return false;
    }
}
