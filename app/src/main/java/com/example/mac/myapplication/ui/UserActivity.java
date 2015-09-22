package com.example.mac.myapplication.ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.example.mac.myapplication.R;

public class UserActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected int getMenuTitleId() {
        return R.string.user;
    }
}
