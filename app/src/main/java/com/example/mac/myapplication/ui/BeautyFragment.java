package com.example.mac.myapplication.ui;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.ui.adapter.MyFragmentPagerAdapter;
import com.example.mac.myapplication.ui.fragment.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeautyFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.indicator_leg)
    TextView indicatorLeg;
    @Bind(R.id.indicator_butt)
    TextView indicatorButt;
    @Bind(R.id.indicator_boob)
    TextView indicatorBoob;
    @Bind(R.id.indicator_stocking)
    TextView indicatorStocking;
    @Bind(R.id.tab_indicator)
    View tabIndicator;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    public static final int BEAUTY_LEG = 0;
    public static final int BEAUTY_BUTT = 1;
    public static final int BEAUTY_BOOB = 2;
    public static final int BEAUTY_STOCKING = 3;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private int indicatorWidth;

    protected void initViews() {
        initIndicator(4);
        initViewPager();
    }

    private void initIndicator(int indicatorNum) {
        Display display = getActivity().getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        indicatorWidth = displayMetrics.widthPixels / indicatorNum;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tabIndicator.getLayoutParams();
        layoutParams.width = indicatorWidth;
        tabIndicator.setLayoutParams(layoutParams);
        indicatorButt.setOnClickListener(this);
        indicatorBoob.setOnClickListener(this);
        indicatorLeg.setOnClickListener(this);
        indicatorStocking.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_beauty;
    }

    private void initViewPager() {
        fragments.add(new BeautyPicFragment());
        fragments.add(new PagerFragmentLike());
        fragments.add(new PagerFragmentLike());
        fragments.add(new PagerFragmentLike());
        viewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tabIndicator.getLayoutParams();
                layoutParams.leftMargin = (int) ((position + positionOffset) * indicatorWidth);
                tabIndicator.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageSelected(int position) {
                int select = getResources().getColor(R.color.dark_text);
                switch (position) {
                    case BEAUTY_LEG:
                        initIndicatorState();
                        indicatorLeg.setTextColor(select);
                        break;
                    case BEAUTY_BUTT:
                        initIndicatorState();
                        indicatorButt.setTextColor(select);
                        break;
                    case BEAUTY_BOOB:
                        initIndicatorState();
                        indicatorBoob.setTextColor(select);
                        break;
                    case BEAUTY_STOCKING:
                        initIndicatorState();
                        indicatorStocking.setTextColor(select);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initIndicatorState() {
        int unSelect = getResources().getColor(R.color.unselect_text);
        indicatorLeg.setTextColor(unSelect);
        indicatorButt.setTextColor(unSelect);
        indicatorBoob.setTextColor(unSelect);
        indicatorStocking.setTextColor(unSelect);
    }

    @Override
    public void onClick(View v) {
        //super.onClick(v);

        switch (v.getId()) {
            case R.id.indicator_leg:
                viewPager.setCurrentItem(BEAUTY_LEG);
                break;
            case R.id.indicator_butt:
                viewPager.setCurrentItem(BEAUTY_BUTT);
                break;
            case R.id.indicator_boob:
                viewPager.setCurrentItem(BEAUTY_BOOB);
                break;
            case R.id.indicator_stocking:
                viewPager.setCurrentItem(BEAUTY_STOCKING);
                break;
        }
    }
}
