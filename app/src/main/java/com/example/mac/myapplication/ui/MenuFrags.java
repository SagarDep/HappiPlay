package com.example.mac.myapplication.ui;

import android.support.v4.app.Fragment;

import com.example.mac.myapplication.R;

/**
 * Created by yons on 15/9/22.
 */
public enum MenuFrags {

    USER(0, R.string.user,new UserFragment()),
    ALBUM(1,R.string.album,new AlbumFragment()),
    LIKE(2,R.string.like,new LikeFragment()),
    SETTING(3,R.string.setting,new SettingFragment()),
    NEARBY(4,R.string.nearby,new NearbyFragment()),
    MSG(5,R.string.message,new MessageFragment());

    private int index;
    private int titleId;
    private Fragment fragment;

    MenuFrags(int index, int titleId, Fragment fragment) {
        this.index = index;
        this.titleId = titleId;
        this.fragment = fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }
}
