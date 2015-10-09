package com.example.mac.myapplication.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;

import com.example.mac.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserEditFragment extends android.support.v4.app.Fragment {
    private android.support.v7.widget.Toolbar toolbar;

    public UserEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        toolbar=BaseActivity.getToolbar();
        toolbar.setTitle("个人资料");
        return inflater.inflate(R.layout.fragment_user_edit, container, false);
    }

}
