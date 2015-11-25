package com.example.mac.myapplication.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.helper.FragmentHelper;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment implements View.OnClickListener {


    @Bind(R.id.edit_profile)
    Button editProfile;
    @Bind(R.id.back)
    ImageView back;
    private Fragment fragment;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        ButterKnife.bind(this, view);
        initButton();
        return view;
    }

    private void initButton() {
        editProfile.setOnClickListener(this);
        back.setOnClickListener(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_profile:
                startUserEdit();
                break;
            case R.id.back:
                FragmentHelper.manager.popBackStack();
                break;
        }
    }

    private void startUserEdit() {
        String tag="user_edit";
        if (fragment==null){
            fragment = new UserEditFragment();
        }
        FragmentHelper.replaceFragment(R.id.content, fragment, tag);
    }
}
