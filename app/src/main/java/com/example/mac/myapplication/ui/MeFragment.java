package com.example.mac.myapplication.ui;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mac.myapplication.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    @Bind(R.id.tab_indicator)
    View tabIndicator;
    @Bind(R.id.indicator_all)
    ImageView indicatorAll;
    @Bind(R.id.indicator_like)
    ImageView indicatorLike;
    private ArrayList<android.support.v4.app.Fragment> views = new ArrayList<>();
    private int indicatorWidth;
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.bind(this, view);
        initViewPager();
        initIndicator(2);
        return view;
    }

    private void initIndicator(int indicatorNum) {
        Display display = getActivity().getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        indicatorWidth = displayMetrics.widthPixels / indicatorNum;

        ViewGroup.LayoutParams layoutParams = tabIndicator.getLayoutParams();
        layoutParams.width = indicatorWidth;
        tabIndicator.setLayoutParams(layoutParams);

        indicatorAll.setOnClickListener(this);
        indicatorLike.setOnClickListener(this);
    }

    private void initViewPager() {
        views.add(new PagerFragmentAll());
        views.add(new PagerFragmentLike());
        viewPager.setAdapter(new MyAdapter(getActivity().getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tabIndicator.getLayoutParams();
                layoutParams.leftMargin = (int) ((position + positionOffset) * indicatorWidth);
                tabIndicator.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    indicatorAll.setImageResource(R.drawable.all_select);
                    indicatorLike.setImageResource(R.drawable.like_unselect);
                }else {
                    indicatorAll.setImageResource(R.drawable.all_unselect);
                    indicatorLike.setImageResource(R.drawable.like_select);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.indicator_all:
                viewPager.setCurrentItem(0);
                break;
            case R.id.indicator_like:
                viewPager.setCurrentItem(1);
                break;
        }
    }

    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return views.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }


        @Override
        public int getCount() {
            return views.size();
        }

    }
}
