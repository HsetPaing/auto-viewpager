package com.santalu.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import com.santalu.autoviewpager.AutoViewPager;

public class SampleActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sample);
    AutoViewPager viewPager = findViewById(R.id.view_pager);
    viewPager.setAdapter(new SamplePagerAdapter(getSupportFragmentManager()));
    //viewPager.setIndeterminate(true);
    //viewPager.setAutoScrollEnabled(true);
  }

  static class SamplePagerAdapter extends FragmentStatePagerAdapter {

    SamplePagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      return SampleFragment.newInstance();
    }

    @Override
    public int getCount() {
      return 3;
    }
  }
}
