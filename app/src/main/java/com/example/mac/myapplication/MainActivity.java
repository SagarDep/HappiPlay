package com.example.mac.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.internal.app.ToolbarActionBar;
import android.text.AndroidCharacter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.mac.myapplication.interf.OnTabReselecctListener;
import com.example.mac.myapplication.ui.BaseActivity;
import com.example.mac.myapplication.ui.HomeFragment;
import com.example.mac.myapplication.ui.MainTab;

import java.util.HashMap;

public class MainActivity extends BaseActivity implements View.OnTouchListener, TabHost.OnTabChangeListener {

    private static final String TAG = "test";

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private View drawerView;
    private FragmentTabHost mTabhost;
    private MainTab[] tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViews();
        initViews();
    }

    private void initViews() {
        initDrawerNBar();
        initTabs();
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment homeFragment = new HomeFragment();
        transaction.replace(R.id.content_frame, homeFragment);
        transaction.commit();
    }


    private void initDrawerNBar() {
        //初始化drawer

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toolbar.setTitle("Home");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                toolbar.setTitle(R.string.app_name);
                invalidateOptionsMenu();
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void initTabs() {
        mTabhost = (FragmentTabHost) findViewById(R.id.tab_host);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.content_frame);

        tabs = MainTab.values();
        final int size = tabs.length;
        for (int i = 0; i < size; i++) {
            MainTab tab = tabs[i];
            TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getResName()));
            View tabIndicator = LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab_indicator, null);
            TextView title = (TextView) tabIndicator.findViewById(R.id.indicator);
            ImageView img = (ImageView) tabIndicator.findViewById(R.id.indicator_icon);
            Drawable drawable = this.getResources().getDrawable(tab.getResIcon());
            if (drawable != null)
                drawable.setBounds(0, 0, title.getMeasuredWidth(), title.getMeasuredHeight() - 10);
//            title.setCompoundDrawablesWithIntrinsicBounds(null, drawable,null,null);
            title.setText(getString(tab.getResName()));
            img.setImageResource(tab.getResIcon());
            img.setScaleType(ImageView.ScaleType.FIT_CENTER);
            tabSpec.setIndicator(tabIndicator);
            tabSpec.setContent(new TabHost.TabContentFactory() {
                @Override
                public View createTabContent(String s) {
                    return new View(MainActivity.this);
                }
            });
            mTabhost.addTab(tabSpec, tab.getClz(), null);
            mTabhost.getTabWidget().setDividerDrawable(null);
            mTabhost.getTabWidget().getChildAt(i).setOnTouchListener(this);
        }
//        mTabhost.setCurrentTab(0);
        mTabhost.setOnTabChangedListener(this);
    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.tb_custom);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = findViewById(R.id.drawer);
    }

    //菜单项的初始化
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //比如打开菜单时隐藏search按钮
        boolean isDrawerOpen = mDrawerLayout.isDrawerOpen(drawerView);//drawerView是整個drawer
        menu.findItem(R.id.action_search).setVisible(!isDrawerOpen);//drawer關閉時則隱藏按鈕
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
    public boolean onTouch(View view, MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        boolean consumed = false;
        //use getTabHost().getCurrentTabView to decide if the current tab is touched again
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && view.equals(mTabhost.getCurrentTabView())) {
            //use getTabHost().getCurrentView to get a handle to the view which is displayed in the tab
            //and to get this views context
            Fragment currentFragment = getcurrentFragment();
            if (currentFragment != null &&
                    currentFragment instanceof OnTabReselecctListener) {
                OnTabReselecctListener listener = (OnTabReselecctListener) currentFragment;
                listener.onTabReselect();
                consumed = true;
            }
        }
        return consumed;
    }

    private Fragment getcurrentFragment() {
        return getSupportFragmentManager().findFragmentByTag(mTabhost.getCurrentTabTag());
    }

    @Override
    public void onTabChanged(String tabId) {
        final int size = mTabhost.getTabWidget().getTabCount();
        for (int i = 0; i < size; i++) {
            boolean isCurrentTab = (i == mTabhost.getCurrentTab());
            View view = mTabhost.getTabWidget().getChildAt(i);
            view.setSelected(isCurrentTab);
            if (isCurrentTab) {
                toolbar.setTitle(tabs[i].getResName());
            }
        }
        if (tabId.equals(getString(MainTab.MESSAGE.getResName()))) {
            //待加badgeView
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}