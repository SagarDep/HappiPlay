package com.example.mac.myapplication.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.helper.FragmentHelper;
import com.example.mac.myapplication.ui.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPwdFragment extends BaseFragment implements View.OnClickListener, TextWatcher {


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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imm = (InputMethodManager) registerUser.getContext().
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(registerUser, InputMethodManager.SHOW_IMPLICIT);
    }

    protected void initViews() {
        findPwd.setTextColor(getResources().getColor(R.color.white));
        if (getArguments() != null) {
            userName = getArguments().getString("userName");
            registerUser.setText(userName);
        }
        registerUser.addTextChangedListener(this);
        back.setOnClickListener(this);
        findPwd.setOnClickListener(this);
    }
    @Override
    protected void AlwaysInit() {
        registerUser.requestFocus();

    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_forget_pwd;
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
                registerUser.setText(userName, TextView.BufferType.EDITABLE);
                imm.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        userName = registerUser.getText().toString().replace(" ", "");

        if (TextUtils.isEmpty(userName)) {
            findPwd.setEnabled(false);
        } else {
            findPwd.setEnabled(true);
        }
    }
}