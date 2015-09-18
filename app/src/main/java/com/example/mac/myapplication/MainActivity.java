package com.example.mac.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
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

import com.example.mac.myapplication.interf.OnTabReselecctListener;
import com.example.mac.myapplication.ui.MainTab;

public class MainActivity extends ActionBarActivity implements View.OnTouchListener {

    private static final String TAG = "test";
//    private DrawerLayout mDrawerLayout;
//    private ListView mDrawerList;
//    private List<String> menuList;
//    private ArrayAdapter<String> adapter;
//    private ActionBarDrawerToggle mDrawerToggle;
//    private String mTitle;

    private android.support.v7.widget.Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private View drawerView;
    private FragmentTabHost mTabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initViews();

    }

    private void initViews() {
        /*
        初始化drawer和actionbar
         */
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));//白色字体
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示home图标
        getSupportActionBar().setHomeButtonEnabled(true);//home可点击
        //ActionBarDrawerToggle是一个控制抽屉开关的类
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,
                R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toolbar.setTitle("Home");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                toolbar.setTitle("AppName");
                invalidateOptionsMenu();
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mTabhost= (FragmentTabHost) findViewById(R.id.tab_host);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.content_frame);

        initTabs();

    }

    private void initTabs() {
        MainTab[] tabs= MainTab.values();
        final int size=tabs.length;
        for (int i=0;i<size;i++) {
            MainTab tab = tabs[i];
            TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getResName()));
            View tabIndicator= LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab_indicator, null);
            TextView title = (TextView) tabIndicator.findViewById(R.id.indicator);
            ImageView img= (ImageView) tabIndicator.findViewById(R.id.indicator_icon);
            Drawable drawable = this.getResources().getDrawable(tab.getResIcon());
            if(drawable!=null)
                drawable.setBounds(0,0,title.getMeasuredWidth(),title.getMeasuredHeight()-10);
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
            mTabhost.addTab(tabSpec, tab.getClz(),null);
            mTabhost.getTabWidget().setDividerDrawable(null);
            mTabhost.getTabWidget().getChildAt(i).setOnTouchListener(this);
        }
    }

    private void findViews() {
        toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.tb_custom);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = findViewById(R.id.drawer);
    }

        //在这里控制菜单项的初始化
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //比如打开菜单时隐藏search按钮
        boolean isDrawerOpen=mDrawerLayout.isDrawerOpen(drawerView);
        menu.findItem(R.id.action_search).setVisible(!isDrawerOpen);
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
        boolean consumed=false;
        //use getTabHost().getCurrentTabView to decide if the current tab is touched again
        if (motionEvent.getAction()==MotionEvent.ACTION_DOWN&&view.equals(mTabhost.getCurrentTabView())){
            //use getTabHost().getCurrentView to get a handle to the view which is displayed in the tab
            //and to get this views context
            Fragment currentFragment=getcurrentFragment();
            if (currentFragment!=null&&
                    currentFragment instanceof OnTabReselecctListener){
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
}
