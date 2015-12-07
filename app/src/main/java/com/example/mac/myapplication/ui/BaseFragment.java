package com.example.mac.myapplication.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.helper.FragmentHelper;

import java.lang.reflect.Field;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    protected void initData() {

    }

    protected void AlwaysInit() {

    }

    protected abstract void initViews();

    protected abstract int getLayoutId();


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                FragmentHelper.manager.popBackStack();
                break;
            case R.id.message:
                FragmentHelper.removeFragmentByTag("me");
                if (FragmentHelper.messageFragment == null) {
                    FragmentHelper.messageFragment = new MessageFragment();
                }
                FragmentHelper.replaceFragment(R.id.content, FragmentHelper.messageFragment, "message");
                break;
        }
    }
}
