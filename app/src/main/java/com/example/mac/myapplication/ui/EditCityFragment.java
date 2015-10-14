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
public class EditCityFragment extends android.support.v4.app.Fragment {


    public EditCityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_city, container, false);
    }


}
