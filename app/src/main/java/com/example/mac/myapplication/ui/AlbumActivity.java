package com.example.mac.myapplication.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.mac.myapplication.R;

public class AlbumActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_album;
    }

    @Override
    protected int getMenuTitleId() {
        return R.string.album;
    }
}
