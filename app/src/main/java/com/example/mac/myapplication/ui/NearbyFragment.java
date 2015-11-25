package com.example.mac.myapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by yons on 15/9/22.
 */
public class NearbyFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.indicator_recent)
    TextView indicatorRecent;
    @Bind(R.id.indicator_guess)
    TextView indicatorGuess;
    @Bind(R.id.indicator_online)
    TextView indicatorOnline;
    @Bind(R.id.indicator_recommend)
    TextView indicatorRecommend;
    @Bind(R.id.tab_indicator)
    View tabIndicator;

    @Bind(R.id.view_pager)
    ViewPager viewPager;
    private RecyclerView mRecyclerView;
    private List<String> mDataList;
    private CardAdapter adapter;
    private Context mContext;
    private int indicatorWidth;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private View rootView;
    public static final int USER_RECENT = 0;
    public static final int USER_GUESS = 1;
    public static final int USER_ONLINE = 2;
    public static final int USER_RECOMMEND = 3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            mContext = getActivity();
            rootView = inflater.inflate(R.layout.fragment_nearby, container, false);
            ButterKnife.bind(this, rootView);
            initIndicator(4);
            initViewPager();
        }

        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void initIndicator(int indicatorNum) {
        Display display = getActivity().getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        indicatorWidth = displayMetrics.widthPixels / indicatorNum;

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tabIndicator.getLayoutParams();
        layoutParams.width = indicatorWidth;
        tabIndicator.setLayoutParams(layoutParams);

        indicatorGuess.setOnClickListener(this);
        indicatorOnline.setOnClickListener(this);
        indicatorRecent.setOnClickListener(this);
        indicatorRecommend.setOnClickListener(this);
    }

    private void initViewPager() {
        fragments.add(new UserRecentFragment());
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
                int select=getResources().getColor(R.color.dark_text);

                switch (position) {
                    case USER_RECENT:
                        initIndicatorState();
                        indicatorRecent.setTextColor(select);
                        break;
                    case USER_GUESS:
                        initIndicatorState();
                        indicatorGuess.setTextColor(select);
                        break;
                    case USER_ONLINE:
                        initIndicatorState();
                        indicatorOnline.setTextColor(select);
                        break;
                    case USER_RECOMMEND:
                        initIndicatorState();
                        indicatorRecommend.setTextColor(select);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initIndicatorState() {
        int unSelect=getResources().getColor(R.color.unselect_text);
        indicatorRecent.setTextColor(unSelect);
        indicatorGuess.setTextColor(unSelect);
        indicatorOnline.setTextColor(unSelect);
        indicatorRecommend.setTextColor(unSelect);
    }

    //菜单项的初始化
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_camera).setVisible(true);
        //比如打开菜单时隐藏search按钮
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            Intent intent = new Intent();
            Uri uri = Uri.parse("http://www.baidu.com");
            intent.setAction(Intent.ACTION_VIEW)
                    .setData(uri);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.indicator_recent:
                viewPager.setCurrentItem(USER_RECENT);
                break;
            case R.id.indicator_guess:
                viewPager.setCurrentItem(USER_GUESS);
                break;
            case R.id.indicator_online:
                viewPager.setCurrentItem(USER_ONLINE);
                break;
            case R.id.indicator_recommend:
                viewPager.setCurrentItem(USER_RECOMMEND);
                break;
        }
    }
}