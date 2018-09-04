# AutoViewPager

[![](https://jitpack.io/v/santalu/auto-viewpager.svg)](https://jitpack.io/#santalu/auto-viewpager) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AutoViewPager-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6038) [![Build Status](https://travis-ci.org/santalu/auto-viewpager.svg?branch=master)](https://travis-ci.org/santalu/auto-viewpager)

## Sample

<img src="https://github.com/santalu/auto-viewpager/blob/master/media/sample.gif"/>

## Usage

### Gradle
```
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```
```
dependencies {
  implementation 'com.github.santalu:auto-viewpager:1.0.5'
}
```

### XML
```xml
 <com.santalu.autoviewpager.AutoViewPager
    android:id="@+id/view_pager"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_above="@+id/indicator"
    app:avp_autoScroll="true"
    app:avp_duration="5000"
    app:avp_indeterminate="true"/>
```

## Attributes

| Name        | Description           | Value  |
| ------------- |:-------------:| -----:|
| avp_indeterminate      | enable/disable infinite paging | boolean |
| avp_autoScroll     | enable/disable auto scrolling      | boolean |
| avp_duration | duration of auto scroll      |   integer |

## License
```
Copyright 2017 Fatih Santalu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

