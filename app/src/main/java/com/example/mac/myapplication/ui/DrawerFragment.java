package com.example.mac.myapplication.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mac.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrawerFragment extends Fragment implements View.OnClickListener {

    private View btnUser;
    private View btnLike;
    private View btnAlbum;
    private View btnSetting;

    public DrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drawer, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        btnUser = view.findViewById(R.id.menu_user);
        btnLike = view.findViewById(R.id.menu_like);
        btnAlbum=view.findViewById(R.id.menu_album);
        btnSetting= view.findViewById(R.id.menu_setting);
        btnUser.setOnClickListener(this);
        btnLike.setOnClickListener(this);
        btnAlbum.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.menu_user:
                intent = new Intent(getActivity(), UserActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_album:
                intent = new Intent(getActivity(), AlbumActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_like:
                intent = new Intent(getActivity(), LikeActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_setting:
                intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
