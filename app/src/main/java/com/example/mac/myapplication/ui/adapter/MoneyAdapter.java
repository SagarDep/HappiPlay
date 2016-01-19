package com.example.mac.myapplication.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mac.myapplication.model.IncomeSimpleModel;
import com.example.mac.myapplication.R;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by happi on 16/1/7.
 */
public class MoneyAdapter extends BaseAdapter {

    Context context;
    List<IncomeSimpleModel> list;
    LayoutInflater inflater;


    public MoneyAdapter (Context context,List<IncomeSimpleModel> list) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.adapter_money,null);
        }
        TextView date = (TextView)view.findViewById(R.id.benefit_date);
        TextView money = (TextView)view.findViewById(R.id.benefit_day_money);
        date.setText(list.get(i).getYmd());


        BigDecimal b = new BigDecimal(list.get(i).getMoney());
        BigDecimal moneyText = b.setScale(3, BigDecimal.ROUND_HALF_UP);
        money.setText("" + moneyText);

        return view;
    }
}
