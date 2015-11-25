package com.example.mac.myapplication.ui;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
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
public class LoginFragment extends Fragment implements View.OnClickListener {

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_login, container, false);
            ButterKnife.bind(this, rootView);
        }
        FragmentHelper.hideTab();
        ButterKnife.bind(this, rootView);
        initViews();
        if (isLogin()) {
            if (fragment == null) {
                fragment = new MeFragment();
            }
            FragmentTransaction transaction = FragmentHelper.manager.beginTransaction();
            transaction.replace(R.id.content, fragment, "me");
            transaction.commit();
        }
        return rootView;
    }

    private void initViews() {
        login.setOnClickListener(this);
        loginUser.setOnClickListener(this);
        loginPwd.setOnClickListener(this);
        register.setOnClickListener(this);
        forgetPwd.setOnClickListener(this);
    }


    public static boolean isLogin() {
        SharedPreferences sp = MainActivity.context.getSharedPreferences("happiplay", Context.MODE_PRIVATE);
        isLogin = sp.getBoolean("isLogin", false);
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
                userName = loginUser.getText().toString().replace(" ", "");
                loginUser.setText(userName, TextView.BufferType.EDITABLE);
                userPwd = loginPwd.getText().toString();
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPwd)) {
                    Toast.makeText(getContext(), "用戶名或者密碼不能為空~", Toast.LENGTH_SHORT).show();
                } else {
                    FragmentHelper.manager.popBackStack();
                }
                break;
            case R.id.login_user:
                break;
            case R.id.login_pwd:
                break;
            case R.id.register:
                userName = loginUser.getText().toString().replace(" ", "");
                loginUser.setText(userName, TextView.BufferType.EDITABLE);
                goNewFragment(REGISTER);
                break;
            case R.id.forget_pwd:
                userName = loginUser.getText().toString().replace(" ", "");
                loginUser.setText(userName, TextView.BufferType.EDITABLE);
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
}
