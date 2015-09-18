package com.example.mac.myapplication.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mac.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_content,container, false);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        //在fragment中使用getArguments得到参数
        String text=getArguments().getString("test");
        textView.setText(">>"+text);
        return view;
    }


}
