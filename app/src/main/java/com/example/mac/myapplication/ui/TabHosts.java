package com.example.mac.myapplication.ui;

import com.example.mac.myapplication.R;

public enum TabHosts {

    HOME(0, R.string.home, R.drawable.indicator_home,
            MoneyFragment.class),

    BEAUTY(1, R.string.beauty, R.drawable.indicator_square,
            BeautyFragment.class),

    ACTIVITY(2, R.string.activity, R.drawable.indicator_msg,
            GamePlayFragment.class),

    USER(3, R.string.user, R.drawable.tab_game,
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


