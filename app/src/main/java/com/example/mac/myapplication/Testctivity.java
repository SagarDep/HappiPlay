package com.example.mac.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;
import com.example.mac.myapplication.ui.PagerFragmentAll;
import com.example.mac.myapplication.ui.PagerFragmentLike;
import com.viewpagerindicator.IconPageIndicator;
import com.viewpagerindicator.IconPagerAdapter;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Testctivity extends FragmentActivity {
    private static final String[] CONTENT = new String[] { "Calendar", "Camera", "Alarms", "Location" };
    private static final int[] ICONS = new int[] {
            R.drawable.indicator_home,
            R.drawable.indicator_square,
            R.drawable.indicator_square,
            R.drawable.indicator_msg,
    };
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    @Bind(R.id.indicator)
    IconPageIndicator indicator;
    private ArrayList<Fragment> views = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testctivity);
        ButterKnife.bind(this);

        initViewPager();
    }

    private void initViewPager() {

        LayoutInflater mInflater = LayoutInflater.from(this);
        View page1 = mInflater.inflate(R.layout.fragment_pager_like, null);
        View page2 = mInflater.inflate(R.layout.fragment_pager_all, null);
        views.add(new PagerFragmentAll());
        views.add(new PagerFragmentLike());
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        indicator.setClickable(true);
    }

    public class MyAdapter extends FragmentPagerAdapter implements IconPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new PagerFragmentLike();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }

//        @Override public int getIconResId(int index) {
//            return ICONS[index];
//        }

        @Override
        public int getIconResId(int index) {
            return R.drawable.indicator_home;
        }

        @Override
        public int getCount() {
            return CONTENT.length;
        }

    }
}
