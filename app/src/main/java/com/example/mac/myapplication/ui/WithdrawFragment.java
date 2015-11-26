package com.example.mac.myapplication.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.helper.FragmentHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class WithdrawFragment extends Fragment implements View.OnClickListener, TextWatcher {

    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.withdraw_history)
    ImageView withdrawHistory;
    @Bind(R.id.withdraw_account)
    EditText withdrawAccount;
    @Bind(R.id.withdraw_amount)
    EditText withdrawAmount;
    @Bind(R.id.withdraw)
    Button withdraw;

    private View rootView;
    private Fragment fragment;
    private InputMethodManager imm;
    private String userName;
    private String amount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_withdraw, container, false);
            ButterKnife.bind(this, rootView);
        }
        ButterKnife.bind(this, rootView);
        initViews();
        return rootView;
    }

    private void initViews() {
        back.setOnClickListener(this);
        withdrawHistory.setOnClickListener(this);
        withdrawAccount.requestFocus();
        withdrawAccount.addTextChangedListener(this);
        withdrawAmount.addTextChangedListener(this);
        withdraw.setOnClickListener(this);
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
            case R.id.withdraw:
                withdrawAccount.setText(userName, TextView.BufferType.EDITABLE);

                FragmentHelper.manager.popBackStack();
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
        userName = withdrawAccount.getText().toString().replace(" ", "");
        amount = withdrawAmount.getText().toString();

        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(amount)) {
            withdraw.setEnabled(false);
        } else {
            withdraw.setEnabled(true);
        }
    }
}
