package com.example.mac.myapplication.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.ui.activity.BaseActivity;

public class MenuActivity extends BaseActivity implements EditNameFragment.UpdateTextListener {
    private MenuFrags menuFrags;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        menuFrags = (MenuFrags) getIntent().getExtras().get("extra");
        if (menuFrags != null) {
            fragment = menuFrags.getFragment();
        }
        super.onCreate(savedInstanceState);


        initFragment();

    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }

    @Override
    protected int getTitleId() {
//        return menuFragment !=null? menuFragment.getTitleId():0;
        return menuFrags.getTitleId();
    }

    @Override
    protected int getLayoutId() {
        int layoutId = R.layout.activity_menu;
//如果是userFragment就用可以透明化toolbar的布局
        if (fragment instanceof UserFragment) {
            layoutId = R.layout.activity_menu_user;
        }
        return layoutId;
    }

    @Override
    public void sendText(String text) {
        UserEditFragment fragment = (UserEditFragment) getSupportFragmentManager().findFragmentByTag("user_edit");
        if (fragment!=null){
            fragment.sendText(text);
        }
    }
}
