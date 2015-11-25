package com.example.mac.myapplication.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.helper.FragmentHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPwdFragment extends Fragment implements View.OnClickListener {


    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.register_user)
    EditText registerUser;
    @Bind(R.id.find_pwd)
    Button findPwd;
    private View rootView;
    private Fragment fragment;
    private String userName;
    private InputMethodManager imm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_forget_pwd, container, false);
            ButterKnife.bind(this, rootView);
        }
        ButterKnife.bind(this, rootView);
        initViews();
        return rootView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imm = (InputMethodManager) registerUser.getContext().
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(registerUser, InputMethodManager.SHOW_IMPLICIT);
    }

    private void initViews() {
        if (getArguments()!=null){
            userName = getArguments().getString("userName");
            registerUser.setText(userName);
        }
        registerUser.requestFocus();
        back.setOnClickListener(this);
        findPwd.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                FragmentHelper.manager.popBackStack();
                break;
            case R.id.find_pwd:
                userName = registerUser.getText().toString().replace(" ", "");
                registerUser.setText(userName, TextView.BufferType.EDITABLE);
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(getContext(), "郵箱不能為空~", Toast.LENGTH_SHORT).show();
                }else {
                    imm.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
                }
                break;
        }
    }
}