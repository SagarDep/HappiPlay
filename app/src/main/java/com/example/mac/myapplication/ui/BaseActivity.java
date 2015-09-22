package com.example.mac.myapplication.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.internal.app.ToolbarActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v7.widget.Toolbar;

import com.example.mac.myapplication.R;


public class BaseActivity extends ActionBarActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId()!=0)
            setContentView(getLayoutId());
        initToolbar();
    }
    protected int getLayoutId() {
        return 0;
    }
    protected int getMenuTitleId(){ return 0;}

    private void initToolbar() {
        toolbar= (Toolbar) findViewById(R.id.tb_custom);
        if (getMenuTitleId()!=0){
            toolbar.setTitle(getMenuTitleId());
        }
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示home图标
            getSupportActionBar().setHomeButtonEnabled(true);//home可点击
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}