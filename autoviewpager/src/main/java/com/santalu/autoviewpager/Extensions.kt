package com.santalu.autoviewpager

import androidx.viewpager.widget.ViewPager

/**
 * Created by fatihsantalu on 20.11.2018
 */

val ViewPager.lastItem: Int?
  get() = adapter?.count?.minus(1)