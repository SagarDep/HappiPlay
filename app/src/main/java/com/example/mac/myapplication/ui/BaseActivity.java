package com.example.mac.myapplication.ui;

import android.app.Activity;
import android.app.Application;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.example.mac.myapplication.R;


public class BaseActivity extends AppCompatActivity {
    public static Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getLayoutId()!=0)
            setContentView(getLayoutId());
        initToolbar();
    }


    protected int getLayoutId() {
        return 0;
    }
    protected int getTitleId(){ return 0;}

    private  void initToolbar() {
        toolbar= (Toolbar) findViewById(R.id.tool_bar);
        if (toolbar!=null){
            if (getTitleId()!=0){
                toolbar.setTitle(getTitleId());
            }
//            setSupportActionBar(toolbar);
//            if (getSupportActionBar()!=null){
//                getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示home图标
//                getSupportActionBar().setHomeButtonEnabled(true);//home可点击
//            }
//            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onBackPressed();
//                }
//            });
        }
    }

    public static Toolbar getToolbar() {
        return toolbar;
    }
}
