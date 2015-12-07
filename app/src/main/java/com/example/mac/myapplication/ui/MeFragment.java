package com.example.mac.myapplication.ui;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.helper.FragmentHelper;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.tab_indicator)
    View tabIndicator;
    @Bind(R.id.indicator_all)
    ImageView indicatorAll;
    @Bind(R.id.indicator_like)
    ImageView indicatorLike;
    @Bind(R.id.message)
    ImageView message;
    @Bind(R.id.setting)
    ImageView setting;
    @Bind(R.id.upload_picture)
    Button uploadPicture;
    @Bind(R.id.browse_picture)
    Button browsePicture;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private int indicatorWidth;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    private Fragment settingFragment;
    private MyFragmentPagerAdapter adapter;
    private Fragment beautyfragment;

    private void initIndicator(int indicatorNum) {
        Display display = getActivity().getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        indicatorWidth = displayMetrics.widthPixels / indicatorNum;

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tabIndicator.getLayoutParams();
        layoutParams.width = indicatorWidth;
        tabIndicator.setLayoutParams(layoutParams);

        indicatorAll.setOnClickListener(this);
        indicatorLike.setOnClickListener(this);
    }

    private void initViewPager() {
        fragments.add(new PagerFragmentAll());
        fragments.add(new PagerFragmentLike());
        if (adapter == null) {
            adapter = new MyFragmentPagerAdapter(getChildFragmentManager(), fragments);
        }
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tabIndicator.getLayoutParams();
                layoutParams.leftMargin = (int) ((position + positionOffset) * indicatorWidth);
                tabIndicator.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    indicatorAll.setImageResource(R.drawable.all_select);
                    indicatorLike.setImageResource(R.drawable.like_unselect);
                } else {
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.indicator_all:
                viewPager.setCurrentItem(0);
                break;
            case R.id.indicator_like:
                viewPager.setCurrentItem(1);
                break;
            case R.id.setting:
                startSetting();
                break;
            case R.id.browse_picture:
                if (beautyfragment == null) {
                    beautyfragment = new BeautyFragment();
                }
                FragmentHelper.replaceFragment(R.id.content, beautyfragment, "beauty");
                break;
            case R.id.upload_picture:
                break;
            case R.id.message:
                if (FragmentHelper.messageFragment == null) {
                    FragmentHelper.messageFragment = new MessageFragment();
                }
                FragmentHelper.replaceFragment(R.id.content, FragmentHelper.messageFragment, "message");
                break;
        }
    }


    private void startSetting() {
        if (settingFragment == null) {
            settingFragment = new SettingFragment();
        }
        FragmentHelper.replaceFragment(R.id.content, settingFragment, "setting");
        FragmentHelper.hideTab();
    }


    @Override
    protected void initViews() {
        initViewPager();
        initIndicator(2);
        setting.setOnClickListener(this);
        message.setOnClickListener(this);
    }

    @Override
    protected void AlwaysInit() {
        FragmentHelper.showTab();
//        initViewPager();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }


}
