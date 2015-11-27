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
public class SettingFragment extends BaseFragment implements View.OnClickListener {


    @Bind(R.id.edit_profile)
    Button editProfile;
    @Bind(R.id.back)
    ImageView back;
    private Fragment fragment;



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

    @Override
    protected void initViews() {
        editProfile.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }
}
