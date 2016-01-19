package com.example.mac.myapplication.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.ui.fragment.TabHosts;


public class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FragmentManager manager;
    private boolean shouldQuit;
    protected TabHosts currentTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getLayoutId() != 0)
            setContentView(getLayoutId());
        initToolbar();
        init();
    }

    private void init() {
        manager = getSupportFragmentManager();
    }


    protected int getLayoutId() {
        return 0;
    }

    protected int getTitleId() {
        return 0;
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        if (toolbar != null) {
            if (getTitleId() != 0) {
                toolbar.setTitle(getTitleId());
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

    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void onBackPressed() {
        Log.i("test",getResources().getString(currentTab.getResName()));
        if (currentTab == TabHosts.HOME || currentTab == TabHosts.BEAUTY ||
                currentTab == TabHosts.ACTIVITY || currentTab == TabHosts.USER) {
            if (shouldQuit) {
                super.onBackPressed();
                return;
            }
            shouldQuit = true;
            Toast.makeText(this, "Press back twice to quit app", Toast.LENGTH_SHORT).show();
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    shouldQuit = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
        }

    }
}
