package com.example.mac.myapplication.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.custom.BadgeView;
import com.example.mac.myapplication.helper.FragmentHelper;

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
    private List<Map<String, Object>> dataList;
    private Fragment withdrawFragment;
    private Fragment messageFragment;
    private InputMethodManager imm;

    protected void initData() {
        dataList = new ArrayList<>();
        Map<String, Object> dayMap = new HashMap<>();
        dayMap.put("week", "周一");
        dayMap.put("date", "09-01");
        dayMap.put("benefit", "20");
        dataList.add(dayMap);
        dayMap = new HashMap<>();
        dayMap.put("week", "周二");
        dayMap.put("date", "09-02");
        dayMap.put("benefit", "20");
        dataList.add(dayMap);
        dayMap = new HashMap<>();
        dayMap.put("week", "周三");
        dayMap.put("date", "09-03");
        dayMap.put("benefit", "50");
        dataList.add(dayMap);
        dayMap = new HashMap<>();
        dayMap.put("week", "周四");
        dayMap.put("date", "09-04");
        dayMap.put("benefit", "50");
        dataList.add(dayMap);
        dayMap = new HashMap<>();
        dayMap.put("week", "周五");
        dayMap.put("date", "09-05");
        dayMap.put("benefit", "5");
        dayMap = new HashMap<>();
        dayMap.put("week", "周六");
        dayMap.put("date", "09-06");
        dayMap.put("benefit", "0");
        dataList.add(dayMap);
        dayMap = new HashMap<>();
        dayMap.put("week", "周日");
        dayMap.put("date", "09-07");
        dayMap.put("benefit", "100");
        dataList.add(dayMap);

    }

    protected void initViews() {

        String[] from = {"week", "date", "benefit"};
        int[] to = {R.id.benefit_weekday, R.id.benefit_date, R.id.benefit_day_money};
        SimpleAdapter adapter = new SimpleAdapter(getContext(), dataList, R.layout.item_day_benefit, from, to);
        listView.setAdapter(adapter);
        withdraw.setOnClickListener(this);
        message.setOnClickListener(this);
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
        super.onClick(v);
        switch (v.getId()) {
            case R.id.withdraw:
                if (withdrawFragment == null) {
                    withdrawFragment = new WithdrawFragment();
                }
                FragmentHelper.removeFragmentByTag("me");
                FragmentHelper.replaceFragment(R.id.content, withdrawFragment, "withdraw");
                FragmentHelper.hideTab();
                break;
        }
    }

}
