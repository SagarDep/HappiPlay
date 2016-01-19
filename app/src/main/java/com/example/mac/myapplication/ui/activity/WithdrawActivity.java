package com.example.mac.myapplication.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.mac.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by happi on 16/1/6.
 */
public class WithdrawActivity extends AppCompatActivity implements View.OnClickListener,TextWatcher {


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
    @Bind(R.id.pay_way)
    RadioGroup payWay;
    @Bind(R.id.withdraw_intro)
    TextView withdrawIntro;
    private InputMethodManager imm;
    private Fragment fragment;
    private String userName;
    private String amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        ButterKnife.bind(this);
        initViews();
    }

    protected void initViews() {
        back.setOnClickListener(this);
        withdrawHistory.setOnClickListener(this);
        payWay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                imm = (InputMethodManager) withdrawAccount.getContext().
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(withdrawAccount, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        withdrawAccount.addTextChangedListener(this);
        withdrawAmount.addTextChangedListener(this);
        withdraw.setOnClickListener(this);
        withdrawIntro.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.withdraw_intro:
                /*Bundle data = new Bundle();
                data.putString("url", "http://www.baidu.com");
                fragment = new WebviewFragment();
                fragment.setArguments(data);
                FragmentHelper.replaceFragment(R.id.withdraw_content, fragment, "webview");*/
                break;
            case R.id.withdraw:
                /*withdrawAccount.setText(userName, TextView.BufferType.EDITABLE);
                FragmentHelper.manager.popBackStack();*/

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
