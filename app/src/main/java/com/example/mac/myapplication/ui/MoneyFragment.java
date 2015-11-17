package com.example.mac.myapplication.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.mac.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoneyFragment extends android.support.v4.app.Fragment {

    private ListView mListView;
    private List<Map<String, Object>> dataList;

    public MoneyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_money, container, false);
        initData();
        initViews(view);
        return view;
    }

    private void initData() {
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

    private void initViews(View view) {
        mListView = (ListView) view.findViewById(R.id.list_view);
        String[] from = {"week", "date", "benefit"};
        int[] to = {R.id.benefit_weekday, R.id.benefit_date, R.id.benefit_day_money};
        SimpleAdapter adapter = new SimpleAdapter(getContext(), dataList,R.layout.item_day_benefit, from, to);
        mListView.setAdapter(adapter);
    }


}
