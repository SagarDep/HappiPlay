package com.example.mac.myapplication.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {
    protected View rootView;
    private Fragment fragment;
    private InputMethodManager imm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, rootView);
            initData();
            initViews();
        }
        ButterKnife.bind(this, rootView);
        AlwaysInit();
        return rootView;
    }

    protected void initData() {

    }

    protected void AlwaysInit() {

    }

    protected abstract void initViews();

    protected abstract int getLayoutId();

}
