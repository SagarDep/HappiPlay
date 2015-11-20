package com.example.mac.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.example.mac.myapplication.helper.FragmentHelper;
import com.example.mac.myapplication.interf.OnTabReselecctListener;
import com.example.mac.myapplication.ui.BaseActivity;
import com.example.mac.myapplication.ui.EditNameFragment;
import com.example.mac.myapplication.ui.HomeFragment;
import com.example.mac.myapplication.ui.TabHosts;
import com.example.mac.myapplication.ui.UserEditFragment;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnTouchListener, TabHost.OnTabChangeListener ,EditNameFragment.UpdateTextListener{

    private static final String TAG = "test";
//    @Bind(R.id.tool_bar)
//    Toolbar toolBar;
//    @Bind(R.id.drawer)
//    View drawerView;
//    @Bind(R.id.drawer_layout)
//    DrawerLayout mDrawerLayout;


    private static FragmentTabHost mTabhost;
    private TabHosts[] tabs;
    public static boolean isDrawerOpen;
    ActionBarDrawerToggle mDrawerToggle;
    public static Activity context;

//    public DrawerLayout getDrawerLayout() {
//        return mDrawerLayout;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
//        initDrawerNBar();
        context=this;
        new FragmentHelper((FragmentActivity) context);
        initTabs();
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment homeFragment = new HomeFragment();
        transaction.replace(R.id.content, homeFragment);
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mDrawerLayout.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mDrawerLayout.closeDrawer(GravityCompat.START);
//            }
//        }, 400);
    }

//
//    private void initDrawerNBar() {
//
//        //初始化drawer
//        mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, toolBar,
//                R.string.drawer_open, R.string.drawer_close) {
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//                invalidateOptionsMenu();
//            }
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//                invalidateOptionsMenu();
//            }
//        };
//        mDrawerToggle.syncState();
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
//    }

    private void initTabs() {
        mTabhost = (FragmentTabHost) findViewById(R.id.tab_host);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.content);

        tabs = TabHosts.values();
        final int size = tabs.length;
        for (int i = 0; i < size; i++) {
            TabHosts tab = tabs[i];

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


    //菜单项的初始化
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //比如打开菜单时隐藏search按钮
//        isDrawerOpen = mDrawerLayout.isDrawerOpen(drawerView);//drawerView是整個drawer
//        menu.findItem(R.id.action_search).setVisible(!isDrawerOpen);//drawer關閉時則隱藏按鈕
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
            Fragment currentFragment = getCurrentFragment();
            if (currentFragment != null &&
                    currentFragment instanceof OnTabReselecctListener) {
                OnTabReselecctListener listener = (OnTabReselecctListener) currentFragment;
                listener.onTabReselect();
                consumed = true;
            }
        }
        return consumed;
    }

    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentByTag(mTabhost.getCurrentTabTag());
    }

    @Override
    public void onTabChanged(String tabId) {
        final int size = mTabhost.getTabWidget().getTabCount();
        for (int i = 0; i < size; i++) {
            boolean isCurrentTab = (i == mTabhost.getCurrentTab());
            View view = mTabhost.getTabWidget().getChildAt(i);
            view.setSelected(isCurrentTab);
//            if (isCurrentTab) {
//                toolBar.setTitle(tabs[i].getResName());
//            }
        }
        if (tabId.equals(getString(TabHosts.ACTIVITY.getResName()))) {
            //待加badgeView
        }
    }
    @Override
    public void sendText(String text) {
        UserEditFragment fragment = (UserEditFragment) getSupportFragmentManager().findFragmentByTag("user_edit");
        if (fragment!=null){
            fragment.sendText(text);
        }
    }
    public static FragmentTabHost getTabhost(){
        return mTabhost;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_layout;
    }
}
