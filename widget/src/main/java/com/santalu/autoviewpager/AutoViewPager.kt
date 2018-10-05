package com.santalu.autoviewpager

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class AutoViewPager : ViewPager, Runnable {

  private var offset = 0f

  var duration = DEFAULT_DURATION
  var indeterminate = false
  var autoScroll = false
    set(value) {
      if (value) start() else stop()
    }

  constructor(context: Context) : super(context)

  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
    init(context, attrs)
  }

  private fun init(context: Context, attrs: AttributeSet?) {
    attrs?.let {
      val a = context.obtainStyledAttributes(it, R.styleable.AutoViewPager)
      with(a) {
        indeterminate = getBoolean(R.styleable.AutoViewPager_avp_indeterminate, false)
        autoScroll = getBoolean(R.styleable.AutoViewPager_avp_autoScroll, false)
        duration = getInt(R.styleable.AutoViewPager_avp_duration, DEFAULT_DURATION)
        recycle()
      }
    }
  }

  override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
    if (indeterminate || autoScroll) {
      val action = ev?.actionMasked
      if (action == MotionEvent.ACTION_DOWN) {
        offset = ev.x
        // reset auto scroll on user interaction
        reset()
      }
    }
    return super.onInterceptTouchEvent(ev)
  }

  @SuppressLint("ClickableViewAccessibility")
  override fun onTouchEvent(ev: MotionEvent?): Boolean {
    if (indeterminate) {
      val action = ev?.actionMasked
      if (action == MotionEvent.ACTION_UP) {
        val currentOffset = ev.x
        if (currentItem == lastItem && offset > currentOffset) {
          post { setCurrentItem(0, false) }
        }
      }
    }
    return super.onTouchEvent(ev)
  }

  override fun run() {
    if (!isShown) return
    currentItem = if (currentItem == lastItem) 0 else currentItem + 1
    start()
  }

  fun start() = postDelayed(this, duration.toLong())

  fun stop() = removeCallbacks(this)

  fun reset() {
    stop()
    start()
  }

  companion object {

    const val DEFAULT_DURATION = 5000
  }
}

internal val ViewPager.lastItem: Int?
  get() = this.adapter?.count?.minus(1)