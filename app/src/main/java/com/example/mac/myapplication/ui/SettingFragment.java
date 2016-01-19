package com.example.mac.myapplication.ui;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.helper.FragmentHelper;
import com.example.mac.myapplication.ui.fragment.BaseFragment;

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
        //super.onClick(v);
        switch (v.getId()) {
            case R.id.edit_profile:
                startUserEdit();
                break;
        }
    }

    private void startUserEdit() {
        String tag = "user_edit";
        if (fragment == null) {
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
