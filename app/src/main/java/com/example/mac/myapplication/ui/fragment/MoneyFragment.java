package com.example.mac.myapplication.ui.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.helper.FragmentHelper;
import com.example.mac.myapplication.model.IncomeModel;
import com.example.mac.myapplication.model.IncomeSimpleModel;
import com.example.mac.myapplication.ui.activity.WithdrawActivity;
import com.example.mac.myapplication.ui.adapter.MoneyAdapter;
import com.example.mac.myapplication.utils.LogUtils;
import com.example.mac.myapplication.utils.MosApplication;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoneyFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.withdraw)
    TextView withdraw;
    @Bind(R.id.list_view)
    ListView listView;
    @Bind(R.id.message)
    ImageView message;
    @Bind(R.id.benefit_today)
    TextView today;
    @Bind(R.id.benefit_all)
    TextView all;
    @Bind(R.id.benefit_remainder)
    TextView remainder;

    private MoneyAdapter adapter;
    private List<IncomeSimpleModel> list = new ArrayList<IncomeSimpleModel>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void initViews() {
        adapter = new MoneyAdapter(getContext(), list);
        listView.setAdapter(adapter);
        withdraw.setOnClickListener(this);
        message.setOnClickListener(this);
    }

    protected void initData() {
        getNetClient().getIncome(MosApplication.getInstance().authInfo.getSessionKey());
    }

    @Override
    protected void AlwaysInit() {
        FragmentHelper.showTab();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_money;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.withdraw:
                Intent intent = new Intent(getContext(), WithdrawActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    protected void onHttpSuccess(String url, Object obj) {
        super.onHttpSuccess(url, obj);
        IncomeModel model = (IncomeModel) obj;

        BigDecimal today1 = new BigDecimal(model.getToday());
        BigDecimal today1_1 = today1.setScale(3, BigDecimal.ROUND_HALF_UP);
        today.setText("" + today1_1);

        BigDecimal all1 = new BigDecimal(model.getAll());
        BigDecimal all1_1 = all1.setScale(3, BigDecimal.ROUND_HALF_UP);
        all.setText("" + all1_1);

        BigDecimal rem1 = new BigDecimal(model.getRemainder());
        BigDecimal rem1_1 = rem1.setScale(3, BigDecimal.ROUND_HALF_UP);
        remainder.setText("" + rem1_1);

        list.clear();
        list.addAll(model.getList());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onHttpError(int result, String url, Object obj) {
        super.onHttpError(result, url, obj);

    }
}
