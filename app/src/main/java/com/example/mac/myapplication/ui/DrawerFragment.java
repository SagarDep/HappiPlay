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
    private View btnNearby;
    private View btnAlbum;
    private View btnSetting;
    private View btnLike;
    private View btnMsg;

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
        btnNearby = view.findViewById(R.id.menu_nearby);
        btnAlbum = view.findViewById(R.id.menu_album);
        btnSetting = view.findViewById(R.id.menu_setting);
        btnLike = view.findViewById(R.id.menu_like);
        btnMsg = view.findViewById(R.id.menu_msg);
        btnUser.setOnClickListener(this);
        btnNearby.setOnClickListener(this);
        btnAlbum.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
        btnLike.setOnClickListener(this);
        btnMsg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        intent = new Intent(getActivity(), MenuActivity.class);
        switch (view.getId()) {
            case R.id.menu_user:
                intent.putExtra("extra", MenuFragment.USER);
                break;
            case R.id.menu_album:
                intent.putExtra("extra", MenuFragment.ALBUM);
                break;
            case R.id.menu_nearby:
                intent.putExtra("extra", MenuFragment.NEARBY);
                break;
            case R.id.menu_setting:
                intent.putExtra("extra", MenuFragment.SETTING);
                break;
            case R.id.menu_like:
                intent.putExtra("extra", MenuFragment.LIKE);
                break;
            case R.id.menu_msg:
                intent.putExtra("extra", MenuFragment.MSG);
                break;
        }
        startActivity(intent);

    }
}
