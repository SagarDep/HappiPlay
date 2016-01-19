package com.example.mac.myapplication.ui.fragment;

import com.example.mac.myapplication.R;

public enum TabHosts {

    HOME(0, R.string.home, R.drawable.indicator_home_selector,
            MoneyFragment.class),

    BEAUTY(1, R.string.nearby, R.drawable.indicator_nearby_selector,
            NearbyFragment.class),

    ACTIVITY(2, R.string.activity, R.drawable.indicator_box_selector,
            BoxFragment.class),

    USER(3, R.string.user, R.drawable.indicator_me_selector,
            MeFragment.class);

    private int index;
    private int resName;
    private int resIcon;
    private Class<?> clz;

    TabHosts(int index, int resName, int resIcon, Class<?> clz) {
        this.index = index;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clz = clz;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}


