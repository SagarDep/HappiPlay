package com.example.mac.myapplication.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mac.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeautyFragment extends android.support.v4.app.Fragment {


    public BeautyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_beauty, container, false);
    }


}
