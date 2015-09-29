package com.example.mac.myapplication.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.mac.myapplication.R;

public class MenuActivity extends BaseActivity {
    private MenuFrags menuFrags;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        menuFrags = (MenuFrags)getIntent().getExtras().get("extra");
        super.onCreate(savedInstanceState);
        if (menuFrags !=null){
            initFragment();
        }
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment= menuFrags.getFragment();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }

    @Override
    protected int getTitleId() {
//        return menuFragment !=null? menuFragment.getTitleId():0;
        return  menuFrags.getTitleId();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_menu;
    }
}
