package com.example.mac.myapplication.ui;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac.myapplication.MainActivity;
import com.example.mac.myapplication.R;
import com.example.mac.myapplication.helper.FragmentHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener, TextWatcher {

    public static final String REGISTER = "register";
    public static final String FORGET = "forget";
    @Bind(R.id.login_user)
    EditText loginUser;
    @Bind(R.id.login_pwd)
    EditText loginPwd;
    @Bind(R.id.login)
    Button login;
    @Bind(R.id.forget_pwd)
    TextView forgetPwd;
    @Bind(R.id.register)
    TextView register;
    private View rootView;
    private Fragment fragment;
    private Fragment forgetOrRegister;
    private static boolean isLogin;
    private String userName;
    private String userPwd;

    protected void initViews() {
        login.setTextColor(getResources().getColor(R.color.white));
        loginUser.addTextChangedListener(this);
        loginPwd.addTextChangedListener(this);
        login.setOnClickListener(this);
        loginUser.setOnClickListener(this);
        loginPwd.setOnClickListener(this);
        register.setOnClickListener(this);
        forgetPwd.setOnClickListener(this);

    }

    @Override
    protected void AlwaysInit() {
        if (isLogin()) {
            Fragment me = FragmentHelper.manager.findFragmentByTag("me");
            if (me == null || fragment == null) {
                //me被remove掉了或者fragment没有初始化
                fragment = new MeFragment();
            }

            FragmentTransaction transaction = FragmentHelper.manager.beginTransaction();
            transaction.replace(R.id.content, fragment, "me");
            transaction.commit();
        } else {
            FragmentHelper.hideTab();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }


    public static boolean isLogin() {
        SharedPreferences sp = MainActivity.context.getSharedPreferences("happiplay", Context.MODE_PRIVATE);
        isLogin = sp.getBoolean("isLogin", true);
        return isLogin;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                loginUser.setText(userName, TextView.BufferType.EDITABLE);
                FragmentHelper.manager.popBackStack();
                break;
            case R.id.login_user:
                break;
            case R.id.login_pwd:
                break;
            case R.id.register:

                goNewFragment(REGISTER);
                break;
            case R.id.forget_pwd:
                goNewFragment(FORGET);
                break;
        }
    }

    private void goNewFragment(String fragmentTag) {
        Fragment fragment = null;
        if (fragmentTag.equals(FORGET)) {
            fragment = new ForgetPwdFragment();
        } else if (fragmentTag.equals(REGISTER)) {
            fragment = new RegisterFragment();
        }
        if (fragment != null) {
            if (!TextUtils.isEmpty(userName)) {
                Bundle data = new Bundle();
                data.putString("userName", userName);
                fragment.setArguments(data);
            }
            FragmentHelper.replaceFragment(R.id.content, fragment, fragmentTag);
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
        userName = loginUser.getText().toString().replace(" ", "");
        userPwd = loginPwd.getText().toString();

        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPwd)) {
            login.setEnabled(false);
        } else {
            login.setEnabled(true);
        }
    }
}
